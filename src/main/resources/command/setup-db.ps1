<#
  setup-db.ps1 — 一鍵建立 Fanshop 的 PostgreSQL 環境（可重複執行，不會刪除既有資料）

  做的事：
    1. 建立角色 fanshop（若不存在）
    2. 建立資料庫 fanshop，擁有者為 fanshop（若不存在）
    3. 帳號隔離：撤銷 PUBLIC 的 CONNECT，只授權 fanshop（其他帳號連不進來）
    4. 以 fanshop 身分載入 ../sql/schema.sql（建表）與 ../sql/data.sql（登入種子）

  前提：PostgreSQL 叢集已初始化、超級使用者已存在。
  若整個 D:\Development\tools\pgsql 被刪除（含執行檔），需先：
    1) 重新下載免安裝版 PostgreSQL 解壓到該路徑
    2) initdb 初始化叢集並設定超級使用者，例如：
       & "<PgHome>\bin\initdb.exe" -D "<PgHome>\data" -U "javaflans@gmail.com" --pwfile=<密碼檔> -A scram-sha-256 --encoding=UTF8 --locale=C
       （--pwfile 指向只含一行密碼的純文字檔）
    3) 啟動伺服器後再執行本腳本

  用法（在本檔所在目錄）：
    $env:PGSUPER_PWD = '<超級使用者密碼>'
    .\setup-db.ps1
#>
param(
    [string]$PgHome    = 'D:\Development\tools\pgsql',
    [string]$SuperUser = 'javaflans@gmail.com',
    [string]$DbHost    = 'localhost',
    [int]   $Port      = 5432
)
$ErrorActionPreference = 'Stop'

# ===== 本專案專屬設定 =====
$AppRole = 'fanshop'
$AppPwd  = 'fanshop'
$AppDb   = 'fanshop'
# =========================

$psql    = Join-Path $PgHome 'bin\psql.exe'
if (-not (Test-Path $psql)) { throw "找不到 psql：$psql（PostgreSQL 執行檔是否存在？）" }

$cmdDir  = Split-Path -Parent $MyInvocation.MyCommand.Path
$sqlDir  = Join-Path (Split-Path -Parent $cmdDir) 'sql'
$schema  = Join-Path $sqlDir 'schema.sql'
$data    = Join-Path $sqlDir 'data.sql'

# 超級使用者密碼（供建角色/建庫/隔離使用）
$superPwd = $env:PGSUPER_PWD
if ([string]::IsNullOrEmpty($superPwd)) {
    $sec = Read-Host -Prompt "請輸入超級使用者 $SuperUser 的密碼" -AsSecureString
    $superPwd = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto(
        [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($sec))
}

# 建角色 + 建庫 + 隔離（以超級使用者在 postgres 庫執行；\gexec 讓建庫不在交易區塊內）
$provision = @'
SELECT 'CREATE ROLE {ROLE} LOGIN PASSWORD ''{PWD}'''
WHERE NOT EXISTS (SELECT FROM pg_roles WHERE rolname = '{ROLE}')\gexec

SELECT 'CREATE DATABASE {DB} OWNER {ROLE} ENCODING ''UTF8'''
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '{DB}')\gexec

REVOKE CONNECT ON DATABASE {DB} FROM PUBLIC;
GRANT  CONNECT ON DATABASE {DB} TO {ROLE};
'@ -replace '{ROLE}', $AppRole -replace '{PWD}', $AppPwd -replace '{DB}', $AppDb

$tmp = Join-Path $env:TEMP "provision-$AppDb.sql"
Set-Content -Path $tmp -Value $provision -Encoding UTF8
try {
    # 先看資料庫是否已存在（決定要不要載入 schema/種子，避免覆蓋既有資料）
    $env:PGPASSWORD = $superPwd
    $exists = & $psql -w -tAc "SELECT 1 FROM pg_database WHERE datname = '$AppDb'" -U $SuperUser -h $DbHost -p $Port -d postgres
    $dbExisted = ("$exists".Trim() -eq '1')

    Write-Host "[1/2] 建立角色/資料庫/隔離 ..." -ForegroundColor Cyan
    & $psql -w -v ON_ERROR_STOP=1 -U $SuperUser -h $DbHost -p $Port -d postgres -f $tmp
    if ($LASTEXITCODE -ne 0) { throw "建立角色/資料庫/隔離失敗（exit $LASTEXITCODE）" }

    if ($dbExisted) {
        Write-Host "[2/2] 資料庫 $AppDb 原本就存在，略過 schema/種子載入（避免覆蓋既有資料）。" -ForegroundColor Yellow
    }
    else {
        Write-Host "[2/2] 載入 schema 與種子資料 ..." -ForegroundColor Cyan
        $env:PGPASSWORD = $AppPwd
        & $psql -w -v ON_ERROR_STOP=1 -U $AppRole -h $DbHost -p $Port -d $AppDb -f $schema
        if ($LASTEXITCODE -ne 0) { throw "載入 schema.sql 失敗（exit $LASTEXITCODE）" }
        & $psql -w -v ON_ERROR_STOP=1 -U $AppRole -h $DbHost -p $Port -d $AppDb -f $data
        if ($LASTEXITCODE -ne 0) { throw "載入 data.sql 失敗（exit $LASTEXITCODE）" }
    }

    Write-Host "完成：資料庫 $AppDb 已就緒（帳號 $AppRole）。" -ForegroundColor Green
}
finally {
    Remove-Item Env:PGPASSWORD -ErrorAction SilentlyContinue
    Remove-Item $tmp -Force -ErrorAction SilentlyContinue
}
