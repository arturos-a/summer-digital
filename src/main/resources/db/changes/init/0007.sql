alter table summer_bank.document
    add column client_uuid varchar(255);
alter table summer_bank.account
    add column owner_uuid varchar(255);