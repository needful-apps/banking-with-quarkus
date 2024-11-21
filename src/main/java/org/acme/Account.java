package org.acme;

import java.util.List;

public class Account {
    String id;
    String name;
    String iban;
    String currency;

    public Account(String id, String name, String iban, String currency) {
        this.id = id;
        this.name = name;
        this.iban = iban;
        this.currency = currency;
    }

    public AccountReply toAccountReply() {
        // Account => AccountReply
        return AccountReply.newBuilder()
                .setId(id)
                .setName(name)
                .setIban(iban)
                .setCurrency(currency)
                .build();
    }
}
