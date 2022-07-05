INSERT INTO summer_bank.product
    (uuid, amount, currency, created, is_active)
VALUES ('3752a19f-f67b-435a-aac8-8f332e3790b5', 21000, 'RUB', current_timestamp, true);

INSERT INTO summer_bank.product
    (uuid, amount, currency, created, is_active)
VALUES ('9e2c19ac-81e4-4a00-a966-1b5ead181aab', 21000, 'RUB', current_timestamp, true);


INSERT INTO summer_bank.account
(account_bic, account_date, account_number, uuid, bank_kpp, corr_account_number, bank_name)
VALUES ('', current_timestamp, '4081781005576010501', '3752a19f-f67b-435a-aac8-8f332e3790b5', '671010011',
        '30101810700000000145', 'АФ "САММЕР ДЕЙ БАНК"');

INSERT INTO summer_bank.account
(account_bic, account_date, account_number, uuid, bank_kpp, corr_account_number, bank_name)
VALUES ('', current_timestamp, '4081781004676010501', '9e2c19ac-81e4-4a00-a966-1b5ead181aab', '671010011',
        '30101810700000000145', 'АФ "САММЕР ДЕЙ БАНК"');
