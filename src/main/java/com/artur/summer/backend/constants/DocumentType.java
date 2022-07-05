package com.artur.summer.backend.constants;

public enum DocumentType {
    CLIENT_INTERNAL("Перевод между своими счетами"),
    BANK_INTERNAL("Внутрибанковский перевод"),
    OTHER_CLIENT_INTERNAL(""),
    EXTERNAL_TRANSFER("Перевод в другой банк"),
    PAYMENT("Оплата услуг");
    private String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
