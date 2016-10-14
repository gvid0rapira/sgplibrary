DROP DATABASE IF EXISTS sgplibrary;
CREATE DATABASE sgplibrary;
GRANT ALL PRIVILEGES ON sgplibrary.* TO 'manager'@'localhost'
	IDENTIFIED BY 'manager' WITH GRANT OPTION;
-- GRANT ALL PRIVILEGES ON stafftools.* TO 'manager'@'%'
-- 	IDENTIFIED BY 'manager' WITH GRANT OPTION;
