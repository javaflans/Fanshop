# Fanshop
spring mvc project by Fanshop

> **狀態：封存（Archived）。** 2018 年的獨立開發專案，2026 年現代化遷移至 Spring Boot 4 / Java 25 / PostgreSQL，僅作為作品與技術參考，不再維護，請勿用於正式環境。

## history
- A shop background platform on 2018.

## technology
- UI/UX: kendo JS
- JAVA base:
	- Spring MVC
	- JPA Model
	- Hibernate
- DataBase: PostgreSQL（2026/10 由 MySQL 遷移）

## 資料庫（PostgreSQL）

原本使用 MySQL，已於 2026/10 遷移至 PostgreSQL。原始 MySQL 的 schema 與資料均已遺失，
現有的 `src/main/resources/sql/schema.sql` 是由 JPA Entity 以 Hibernate 反推產生（欄位採小寫），
`data.sql` 為最小可登入種子資料。

### 建立資料庫與帳號

以超級使用者連線後執行：

```sql
CREATE ROLE fanshop LOGIN PASSWORD 'fanshop';
CREATE DATABASE fanshop OWNER fanshop ENCODING 'UTF8';
```

### 建表與種子資料

```powershell
$pg='D:\Development\tools\pgsql\bin'
$env:PGPASSWORD='fanshop'
& "$pg\psql.exe" -U fanshop -h localhost -d fanshop -f src\main\resources\sql\schema.sql
& "$pg\psql.exe" -U fanshop -h localhost -d fanshop -f src\main\resources\sql\data.sql
```

連線資訊預設寫在 `application.properties`，可用環境變數 `DB_URL` / `DB_USER` / `DB_PASSWORD` 覆寫。

### 預設登入帳號（種子資料）

| 項目 | 值 |
| --- | --- |
| 帳號（Email） | `admin@fanshop.local` |
| 密碼 | `admin123` |

> 密碼為明文比對（原程式前後端皆未雜湊）。登入後的首頁（/overview）需要選單與商品等業務資料才能完整顯示，種子資料僅保證「能登入」。