create table summer_bank.system_info
(
    id   int,
    code varchar
);
create table summer_bank.CLIENT_INFO
(
    uuid               varchar(255) PRIMARY KEY,
    first_name         varchar(255),
    last_name          varchar(255),
    middle_name        varchar(255),
    login              varchar(255),
    secret_hash        varchar(255),
    secret_date        timestamp,
    address            varchar(255),
    certificate_number varchar(255),
    created            timestamp,
    status             varchar(255),
    updated            timestamp
);

INSERT INTO summer_bank.CLIENT_INFO
(UUID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, LOGIN, SECRET_HASH, SECRET_DATE, ADDRESS, CERTIFICATE_NUMBER, CREATED,
 STATUS)
VALUES ('fc7563e0-6a1a-4290-a5db-16120eb71f3b', 'Аристарх', 'Сидоров', 'Ибрагимович', 'SAI',
        '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', current_timestamp,
        'Москва, ул. Академика Королева 12', '8901 789786', current_timestamp, 'ACTIVE');
create table summer_bank.client_session
(
    uuid         varchar(255),
    client_uuid  varchar(255),
    created      timestamp,
    updated      timestamp,
    expired_date timestamp
);

create table summer_bank.account
(
    account_bic    varchar(255),
    account_date   timestamp,
    account_number varchar(255),
    uuid           varchar(255) not null,
    primary key (uuid)
);
create table summer_bank.card
(
    account_uuid varchar(255),
    card_number  varchar(255),
    card_owner   varchar(255),
    expired_date varchar(255),
    uuid         varchar(255) not null,
    primary key (uuid)
);



create table summer_bank.credit
(
    account_number         varchar(255),
    annual_pay             numeric(19, 2),
    credit_contract_number varchar(255),
    credit_end_date        timestamp,
    percent                numeric(19, 2),
    uuid                   varchar(255) not null,
    primary key (uuid)
);
create table summer_bank.deposit
(
    account_bic     varchar(255),
    account_date    timestamp,
    account_number  varchar(255),
    contract_number varchar(255),
    percent         numeric(19, 2),
    uuid            varchar(255) not null,
    primary key (uuid)
);
create table summer_bank.product
(
    uuid      varchar(255) not null,
    amount    numeric(19, 2),
    currency  varchar(3),
    created   timestamp,
    is_active boolean      not null,
    updated   timestamp,
    primary key (uuid)
);
alter table if exists summer_bank.account add constraint fk_account_product foreign key (uuid) references summer_bank.product;
alter table if exists summer_bank.card add constraint fk_card_product foreign key (uuid) references summer_bank.product;
alter table if exists summer_bank.credit add constraint fk_credit_product foreign key (uuid) references summer_bank.product;
alter table if exists summer_bank.deposit add constraint fk_deposit_product foreign key (uuid) references summer_bank.product;

INSERT INTO summer_bank.product
    (uuid, amount, currency, created, is_active)
VALUES ('b2159f63-e419-4100-b1a1-2dae400e26bd', 100000, 'RUB', current_timestamp, true);

INSERT INTO summer_bank.account
    (account_bic, account_date, account_number, uuid)
VALUES ('', current_timestamp, '40817810055760515501', 'b2159f63-e419-4100-b1a1-2dae400e26bd');

INSERT INTO summer_bank.product
    (uuid, amount, currency, created, is_active)
VALUES ('df7e40f0-3d7b-47b3-bb6e-cc6395c4201d', 100000, 'RUB', current_timestamp, true);

INSERT INTO summer_bank.card
    (account_uuid, card_number, card_owner, expired_date, uuid)
VALUES ('b2159f63-e419-4100-b1a1-2dae400e26bd', '4276****4227', 'MR. ARISTARKH SIDOROV', '01/22',
        'df7e40f0-3d7b-47b3-bb6e-cc6395c4201d');

CREATE TABLE summer_bank.product_info
(
    uuid             varchar(255) NOT NULL,
    alias            varchar(255) NULL,
    created          timestamp NULL,
    is_active        bool         NOT NULL,
    is_visible       bool         NOT NULL,
    client_info_uuid varchar(255) NULL,
    product_uuid     varchar(255) NULL,
    type             varchar(255) NULL,
    CONSTRAINT product_info_pkey PRIMARY KEY (uuid)
);


-- summer_bank.product_info foreign keys

ALTER TABLE summer_bank.product_info
    ADD CONSTRAINT fk1rw5ifekcyvpg0fc76wtaym7p FOREIGN KEY (client_info_uuid) REFERENCES summer_bank.client_info (uuid);
ALTER TABLE summer_bank.product_info
    ADD CONSTRAINT fkcuwqf90hrplqgax55u8whnj2j FOREIGN KEY (product_uuid) REFERENCES summer_bank.product (uuid);

INSERT INTO summer_bank.product_info
(uuid, alias, created, is_active, is_visible, client_info_uuid, product_uuid, type)
VALUES ('fa992b22-890e-4e59-aa94-17877a53a689', 'Моя Карта', current_timestamp, true, true,
        'fc7563e0-6a1a-4290-a5db-16120eb71f3b', 'df7e40f0-3d7b-47b3-bb6e-cc6395c4201d', 'CARD');

INSERT INTO summer_bank.product_info
(uuid, alias, created, is_active, is_visible, client_info_uuid, product_uuid, type)
VALUES ('3eb7f42e-2875-4507-b7ba-52a1701543a5', 'Зарплатный счет', current_timestamp, true, true,
        'fc7563e0-6a1a-4290-a5db-16120eb71f3b', 'b2159f63-e419-4100-b1a1-2dae400e26bd', 'CARD');