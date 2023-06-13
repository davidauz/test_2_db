* Run this script on your SQL Server:

<pre>
USE master;

IF EXISTS (
    SELECT 1
    FROM sys.databases
    WHERE name = 'TESTDB'
)
BEGIN
	drop database TESTDB;
END

create database TESTDB;

IF EXISTS (SELECT * FROM sys.database_principals where name=N'mssql_user')
drop user mssql_user;
go

if exists (select loginname from syslogins where name=N'mssql_user')
drop login mssql_user;
go

CREATE LOGIN mssql_user WITH PASSWORD = 'mssql_password';

go

GRANT CONNECT SQL TO mssql_user;

go

USE TESTDB;

CREATE USER mssql_user FOR LOGIN mssql_user;

GRANT SELECT, INSERT, DELETE TO mssql_user;

--use TESTDB;

CREATE TABLE MSSQL_TABLE (
    id INT IDENTITY(1,1) PRIMARY KEY,
    VARCHAR_COLUMN VARCHAR(255)
);
</pre>

* And this other script in your mysql or mariadb server:

<pre>
DROP DATABASE IF EXISTS your_database_name;


DROP USER IF EXISTS 'mysql_user'@'localhost';


CREATE DATABASE IF NOT EXISTS testdb;

CREATE USER 'mysql_user'@'localhost' IDENTIFIED BY 'mysql_password';

GRANT USAGE ON *.* TO 'mysql_user'@'localhost' IDENTIFIED BY 'mysql_password';

GRANT ALL PRIVILEGES ON testdb.* TO 'mysql_user'@'localhost';

use testdb;



DROP TABLE IF EXISTS mysqlTable;

CREATE TABLE mysqlTable (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    column_long BIGINT,
    column_date TIMESTAMP,
    column_string VARCHAR(255)
);

DROP TABLE IF EXISTS testdb.hibernate_sequence;
CREATE TABLE testdb.hibernate_sequence (next_val BIGINT) ENGINE=InnoDB;
INSERT INTO testdb.hibernate_sequence (next_val) VALUES (1);


COMMIT;
</pre>
