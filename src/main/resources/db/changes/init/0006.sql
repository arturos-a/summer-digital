CREATE TABLE summer_bank.document
(
    uuid               varchar(255) NOT NULL,
    document_type      varchar(255) NOT NULL,
    created            timestamp NULL,
    doc_data           json NULL,
    updated            timestamp NULL,
    status             varchar(255),
    status_description text,
    CONSTRAINT data_entity_pkey PRIMARY KEY (uuid)
);