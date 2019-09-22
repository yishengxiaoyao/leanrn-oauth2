# leanrn-oauth2
需要创建新数据库
```sql
CREATE DATABASE clientdb;
use clientdb;
create table client_user(
    id bigint auto_increment primary key,
    username varchar(100),
    password varchar(50),
    access_token varchar(100) NULL,
    access_token_validity datetime NULL,
    refresh_token varchar(100) NULL
);

insert into client_user
(username, password)
value
('xiaoyao', '123456');
```
使用`*`resttemplate请求`*`server项目时，需要创建数据库，使用上面的sql执行。
implicitresttemplate的项目稍微有点问题，其他项目可以使用。
