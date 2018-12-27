TRUNCATE TABLE DEVICE CASCADE;
TRUNCATE TABLE DEVICE_TYPE CASCADE;
TRUNCATE TABLE TRANSFER CASCADE;
TRUNCATE TABLE WAREHOUSE CASCADE;
TRUNCATE TABLE USERDATA CASCADE;
TRUNCATE TABLE OFFICE CASCADE;
TRUNCATE TABLE DEPARTMENT CASCADE;
TRUNCATE TABLE COMPANY CASCADE;
TRUNCATE TABLE ADDRESS CASCADE;
TRUNCATE TABLE OAUTH_CLIENT_DETAILS CASCADE;
TRUNCATE TABLE AUTHORITY CASCADE;
TRUNCATE TABLE ADDRESS CASCADE;
TRUNCATE TABLE USER_ CASCADE;
TRUNCATE TABLE USER_ROLE CASCADE;
TRUNCATE TABLE USERS_ROLES_AUTHORITIES CASCADE;
TRUNCATE TABLE NOTIFICATION CASCADE;


INSERT INTO ADDRESS(ID, VERSION,CITY,DELETED, BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (1,0, 'Kraków',FALSE, 1, 'Street X', '12-341');
INSERT INTO ADDRESS(ID, VERSION, CITY,DELETED , BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (2,0, 'Warszawa',FALSE, 2, 'Street Y', '12-342');
INSERT INTO ADDRESS(ID, VERSION, CITY,DELETED , BUILDING_NUMBER, STREET, ZIP_CODE,FLAT_NUMBER) VALUES (3,0, 'Łódź',FALSE, 3, 'Street Z', '12-343',2);
INSERT INTO ADDRESS(ID, VERSION, CITY, DELETED, BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (4,0, 'Lódz',FALSE,  4, 'Street XX', '12-344');
INSERT INTO ADDRESS(ID, VERSION, CITY,DELETED , BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (5,0, 'Rzeszów',FALSE,  5, 'Street YY', '12-345');
INSERT INTO ADDRESS(ID, VERSION, CITY, DELETED, BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (6,0, 'Lublin',FALSE,  6, 'Street ZZ', '12-346');
INSERT INTO ADDRESS(ID, VERSION, CITY,DELETED , BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (7,0, 'Sopot',FALSE , 7, 'Street XXX', '12-347');

INSERT INTO COMPANY(ID, VERSION, DELETED,NAME, MAIN_OFFICE_ADRESS_ID,DESCRIPTION) VALUES (1,0,FALSE, 'Transition Technologies',1, 'OPISsdsdsdsdsdsdsdsd  sdsdsdddd dddddddddddddddd dddddddddd ddddddddddd dddddddddddd dddddddddddddddd dddddddddddddddddddd dddddddddddddddddddddd ddddddddddd');


INSERT INTO DEPARTMENT(ID, VERSION, DELETED,NAME, COMPANY_ID,DESCRIPTION) VALUES (1,0,FALSE, 'Sales & Marketing', 1,'OPISsdsdsdsdsdsdsdsd  sdsdsdddd dddddddddddddddd dddddddddd ddddddddddd dddddddddddd dddddddddddddddd dddddddddddddddddddd dddddddddddddddddddddd ddddddddddd');
INSERT INTO DEPARTMENT(ID, VERSION,DELETED ,NAME, COMPANY_ID) VALUES (2,0,FALSE, 'Research & Development', 1);
INSERT INTO DEPARTMENT(ID, VERSION,DELETED, NAME, COMPANY_ID) VALUES (3,0,FALSE, 'Administration', 1);
INSERT INTO DEPARTMENT(ID, VERSION, DELETED,NAME, COMPANY_ID) VALUES (4,0,FALSE ,'Human Resources', 1);
INSERT INTO DEPARTMENT(ID, VERSION, DELETED,NAME, COMPANY_ID) VALUES (5,0,FALSE ,'Sales & Marketing', 1);


 INSERT INTO OFFICE(ID, VERSION, DELETED,NAME, ADDRESS_ID, DEPARTMENT_ID,DESCRIPTION) VALUES (1,0,FALSE , 'Office of S&M Boston', 4, 1,'OPIS');
 INSERT INTO OFFICE(ID, VERSION,DELETED, NAME, ADDRESS_ID, DEPARTMENT_ID) VALUES (2,0,FALSE , 'Office of S&M New York', 5, 1);
 INSERT INTO OFFICE(ID, VERSION,DELETED, NAME, ADDRESS_ID, DEPARTMENT_ID) VALUES (3,0, FALSE ,'Office of R&D Boston', 6, 2);
 INSERT INTO OFFICE(ID, VERSION,DELETED, NAME, ADDRESS_ID, DEPARTMENT_ID) VALUES (4,0, FALSE ,'Office of A Los Angeles', 7, 3);


 INSERT INTO USERDATA(ID, VERSION, NAME, SURNAME, EMAIL,ADDRESS_ID ) VALUES (1,0, 'John', 'William','email@email.com', 1);
 INSERT INTO USERDATA(ID, VERSION, NAME, SURNAME, ADDRESS_ID ) VALUES (2,0, 'Robert', 'James', 2);
 INSERT INTO USERDATA(ID, VERSION, NAME, SURNAME, ADDRESS_ID ) VALUES (3,0, 'Donald', 'Tyler', 3);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api',
	/*spring-security-oauth2-read-client-password1234*/'$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',
	'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',
	/*spring-security-oauth2-read-write-client-password1234*/'$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',
	'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (1,0, 'COMPANY_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (2,0, 'COMPANY_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (3,0, 'COMPANY_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (4,0, 'COMPANY_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (5,0, 'COMPANY_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (6,0, 'DEPARTMENT_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (7,0, 'DEPARTMENT_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (8,0, 'DEPARTMENT_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (9,0, 'DEPARTMENT_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (10,0, 'DEPARTMENT_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (11,0, 'DELIVERY_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (12,0, 'DELIVERY_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (13,0, 'DELIVERY_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (14,0, 'DELIVERY_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (15,0, 'DELIVERY_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (16,0, 'DEVICE_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (17,0, 'DEVICE_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (18,0, 'DEVICE_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (19,0, 'DEVICE_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (20,0, 'DEVICE_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (21,0, 'DEVICE_USER_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (22,0, 'DEVICE_TYPE_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (23,0, 'DEVICE_TYPE_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (24,0, 'DEVICE_TYPE_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (25,0, 'DEVICE_TYPE_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (26,0, 'DEVICE_TYPE_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (27,0, 'NOTIFICATION_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (28,0, 'NOTIFICATION_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (29,0, 'NOTIFICATION_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (30,0, 'NOTIFICATION_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (31,0, 'NOTIFICATION_USER_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (32,0, 'OFFICE_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (33,0, 'OFFICE_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (34,0, 'OFFICE_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (35,0, 'OFFICE_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (36,0, 'OFFICE_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (37,0, 'SYSTEM_MESSAGE_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (38,0, 'SYSTEM_MESSAGE_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (39,0, 'SYSTEM_MESSAGE_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (40,0, 'SYSTEM_MESSAGE_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (41,0, 'SYSTEM_MESSAGE_LIST_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (42,0, 'TRANSFER_CREATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (43,0, 'TRANSFER_READ');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (44,0, 'TRANSFER_UPDATE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (45,0, 'TRANSFER_DELETE');
INSERT INTO AUTHORITY(ID, VERSION, NAME) VALUES (46,0, 'TRANSFER_LIST_READ');

INSERT INTO USER_(ID, VERSION, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED,USERDATA_ID,OFFICE_ID)
  VALUES (1,0, 'admin', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', FALSE, FALSE, FALSE, TRUE,1,1);

INSERT INTO USER_(ID, VERSION, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED,USERDATA_ID,OFFICE_ID)
  VALUES (2,0, 'reader', /*reader1234*/'$2a$08$dwYz8O.qtUXboGosJFsS4u19LHKW7aCQ0LXXuNlRfjjGKwj5NfKSe', FALSE, FALSE, FALSE, TRUE,2,2);

INSERT INTO USER_(ID, VERSION, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED,USERDATA_ID,OFFICE_ID)
  VALUES (3,0, 'modifier', /*modifier1234*/'$2a$08$kPjzxewXRGNRiIuL4FtQH.mhMn7ZAFBYKB3ROz.J24IX8vDAcThsG', FALSE, FALSE, FALSE, TRUE,3,3);

INSERT INTO USER_(ID, VERSION, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED,USERDATA_ID,OFFICE_ID)
  VALUES (4,0, 'reader2', /*reader1234*/'$2a$08$vVXqh6S8TqfHMs1SlNTu/.J25iUCrpGBpyGExA.9yI.IlDRadR6Ea', FALSE, FALSE, FALSE, TRUE,3,4);

INSERT INTO USER_ROLE(ID,VERSION,NAME,ACTIVE) VALUES (1,0,'ADMIN',TRUE );
INSERT INTO USER_ROLE(ID,VERSION,NAME,ACTIVE) VALUES (2,0,'USER',TRUE );
INSERT INTO USER_ROLE(ID,VERSION,NAME,ACTIVE) VALUES (3,0,'MANAGER',TRUE );
INSERT INTO USER_ROLE(ID,VERSION,NAME,ACTIVE) VALUES (4,0,'WAREHOUSEMAN',TRUE );

INSERT INTO USERS_ROLES(USER_ID,USER_ROLE_ID) VALUES(1,1);
INSERT INTO USERS_ROLES(USER_ID,USER_ROLE_ID) VALUES(1,2);
INSERT INTO USERS_ROLES(USER_ID,USER_ROLE_ID) VALUES(1,3);
INSERT INTO USERS_ROLES(USER_ID,USER_ROLE_ID) VALUES(1,4);

INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 3);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 4);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 5);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 6);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 7);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 8);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 9);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 10);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 11);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 12);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 13);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 14);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 15);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 16);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 17);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 18);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 19);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 20);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 21);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 22);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 23);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 24);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 25);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 26);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 27);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 28);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 29);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 30);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 31);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 32);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 33);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 34);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 35);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 36);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 37);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 38);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 39);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 40);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 41);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 42);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 43);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 44);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 45);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 46);


INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (2, 2);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (2, 6);

INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 3);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 7);

INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (4, 9);

INSERT  INTO WAREHOUSE (ID, VERSION,DELETED,USER_ID,NAME) VALUES (1,0,FALSE ,1,'admin');
INSERT INTO  WAREHOUSE(ID, VERSION,DELETED,USER_ID,NAME,OFFICE_ID) VALUES (2,0,FALSE ,1,'OFFICE_WAREHOUSE',1);

INSERT INTO device_type(ID,VERSION,DELETED,NAME) VALUES (1,0,FALSE ,'COMPUTER');

INSERT INTO DEVICE(ID, VERSION,DELETED,NAME,SERIAL_NUMBER, WAREHOUSE_ID,DEVICE_TYPE_ID) VALUES (1,0,FALSE ,'Komputer', 'XYZ10ABC', 1,1);
INSERT INTO DEVICE(ID,VERSION,DELETED,NAME, SERIAL_NUMBER, WAREHOUSE_ID,DEVICE_TYPE_ID) VALUES (2,0,FALSE ,'Monitor', 'XYZ11ABC', 1,1);
 INSERT INTO DEVICE(ID,VERSION,DELETED,NAME, SERIAL_NUMBER, WAREHOUSE_ID,DEVICE_TYPE_ID) VALUES (3,0,FALSE ,'Klawiatura-bezprzewodowa', 'XYZ12ABC', 1,1);
INSERT INTO DEVICE(ID,VERSION,DELETED,NAME, SERIAL_NUMBER, WAREHOUSE_ID,DEVICE_TYPE_ID) VALUES (4,0,FALSE ,'Myszka-laserowa' ,'XYZ13ABC', 1,1);

INSERT INTO TRANSFER (ID,VERSION,DELETED,USERNAME,TITLE,TRANSFER_DATE,SENDER_WAREHOUSE_ID,RECIEVER_WAREHOUSE_ID,DEVICE_ID,STATUS) VALUES (1,0,FALSE,'admin' ,'TITLE','2011-03-12',1,2,1,'TRANSFERED');

INSERT INTO notification(ID,VERSION,DELETED,TITLE,DESCRIPTION,URL,READED,NOTIFICATION_DATE,USER_ID) VALUES (1,0,FALSE ,'Title','Description','/devices/transfer/view/1',FALSE ,'2011-03-12',1);
INSERT INTO notification(ID,VERSION,DELETED,TITLE,DESCRIPTION,URL,READED,NOTIFICATION_DATE,USER_ID) VALUES (2,0,FALSE ,'Title','Description','/devices/transfer/view/2',FALSE ,'2011-03-12',1);