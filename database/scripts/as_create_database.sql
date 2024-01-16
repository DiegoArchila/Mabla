/******************************************
* SCRIPT CREATION STRUCTURE ON POSTGRESQL *
*******************************************/

--DROP ROLE IF EXISTS as_admin;

-- CREATE ROLE: as_admin
CREATE ROLE as_admin WITH
	SUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	LOGIN
	CONNECTION LIMIT 1
	PASSWORD 'Admin.123456';

--DROP DATABASE IF EXISTS astart;

----- CREATE DATABASE: astart
CREATE DATABASE astart
	WITH
	OWNER = as_admin
	ENCODING = 'UTF8'
	LC_COLLATE = 'en_US.UTF-8'
	LC_CTYPE = 'en_US.UTF-8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;