INSERT INTO summer_bank.product
    (uuid, amount, currency, created, is_active)
VALUES ('aed083a6-fe09-4a4d-b4b1-eb9ef684753f', 40000, 'RUB', current_timestamp, true);

INSERT INTO summer_bank.card
    (account_uuid, card_number, card_owner, expired_date, uuid)
VALUES ('9e2c19ac-81e4-4a00-a966-1b5ead181aab', '5132****5647', 'MR. ARISTARKH SIDOROV', '01/24',
        'aed083a6-fe09-4a4d-b4b1-eb9ef684753f');


INSERT INTO summer_bank.product
    (uuid, amount, currency, created, is_active)
VALUES ('3cd3afe3-cd16-4f68-b21b-37fcb071b8ac', 21000, 'RUB', current_timestamp, true);

INSERT INTO summer_bank.card
    (account_uuid, card_number, card_owner, expired_date, uuid)
VALUES ('3752a19f-f67b-435a-aac8-8f332e3790b5', '7690****4190', 'MR. ARISTARKH SIDOROV', '04/24',
        '3cd3afe3-cd16-4f68-b21b-37fcb071b8ac');
INSERT INTO summer_bank.product_info
(uuid, alias, created, is_active, is_visible, client_info_uuid, product_uuid, type)
VALUES ('6d920973-1a4c-471d-a628-942378402688', 'Важная карта', current_timestamp, true, true,
        'fc7563e0-6a1a-4290-a5db-16120eb71f3b', '3cd3afe3-cd16-4f68-b21b-37fcb071b8ac', 'CARD');

INSERT INTO summer_bank.product_info
(uuid, alias, created, is_active, is_visible, client_info_uuid, product_uuid, type)
VALUES ('e34f9ecd-6192-457b-aed0-24d791182e96', 'Карта запрплатная 2', current_timestamp, true, true,
        'fc7563e0-6a1a-4290-a5db-16120eb71f3b', 'aed083a6-fe09-4a4d-b4b1-eb9ef684753f', 'CARD');
