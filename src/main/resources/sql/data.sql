-- Fanshop 最小可登入種子資料
-- 登入查詢：member JOIN status(mailStatus) WHERE usermail=? AND userpassword=? AND stutype='validated' AND usagetable='member'
-- 密碼為明文比對（前後端皆未雜湊）
INSERT INTO status (id, stutype, usagetable, studesc, usercreated)
VALUES (1, 'validated', 'member', '已驗證會員', 'seed')
ON CONFLICT (id) DO NOTHING;

INSERT INTO member (id, usermail, username, userpassword, mailstatus, userstatus,
                    businesstarget, hottarget, sharetarget, transtarget, userproductcount, zipcode,
                    usercreated)
VALUES (1, 'admin@fanshop.local', 'admin', 'admin123', 1, 1,
        0, 0, 0, 0, 0, 0,
        'seed')
ON CONFLICT (id) DO NOTHING;

-- 角色與會員-角色關聯（登入後 searchRoleByMember 需要）
INSERT INTO role (id, rolename, roledesc, usercreated)
VALUES (1, 'ADMIN', '系統管理員', 'seed')
ON CONFLICT (id) DO NOTHING;

INSERT INTO role_member (id, memberid, roleid, usercreated)
VALUES (1, 1, 1, 'seed')
ON CONFLICT (id, memberid) DO NOTHING;
