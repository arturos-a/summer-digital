create table system_info(id int, code varchar);
create table PUBLIC.CLIENT_INFO(uuid varchar(255), first_name varchar(255),
                        last_name varchar(255), middle_name varchar(255),
                        login varchar(255), secret_hash varchar(255),
                        secret_date timestamp, address varchar(255),
                        certificate_number varchar(255), created timestamp,
                         status varchar(255), updated timestamp);

INSERT INTO PUBLIC.CLIENT_INFO
("UUID", FIRST_NAME, LAST_NAME, MIDDLE_NAME, LOGIN, SECRET_HASH, SECRET_DATE, ADDRESS, CERTIFICATE_NUMBER, CREATED, STATUS)
VALUES('fc7563e0-6a1a-4290-a5db-16120eb71f3b', 'Сидоров', 'Аристарх', 'Ибрагимович', 'SAI', '', current_timestamp, 'Москва, ул. Академика Королева 12', '8901 789786', current_timestamp, 'ACTIVE');