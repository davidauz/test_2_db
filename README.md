You will need to run this script on your SQL Server:

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

