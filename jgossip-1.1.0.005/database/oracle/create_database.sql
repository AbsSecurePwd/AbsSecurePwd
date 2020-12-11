CREATE TABLESPACE forum_db 
    LOGGING 
    DATAFILE 'forum_db.ora' SIZE 50M 
    EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT  AUTO 
	;

CREATE USER DB_USER  PROFILE DEFAULT 
    IDENTIFIED BY DB_PASSWORD DEFAULT TABLESPACE forum_db 
    ACCOUNT UNLOCK
	;

GRANT CONNECT TO DB_USER WITH ADMIN OPTION
;

GRANT RESOURCE TO DB_USER WITH ADMIN OPTION
;

