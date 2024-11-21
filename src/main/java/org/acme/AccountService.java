package org.acme;


import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AccountService {
    List<Account> accounts = new ArrayList<>(List.of(
            new Account("1", "Alice", "DE89370400440532013000", "EUR"),
            new Account("2", "Bob", "DE89370400440532013001", "EUR"),
            new Account("3", "Charlie", "DE89370400440532013002", "EUR")
    ));

    public Account getAccountById(String id) {
        return accounts.stream()
                .filter(account -> account.id.equals(id))
                .findFirst()
                .orElse(new Account("0", "Not found", "", ""));


    }

    public String addAccount(String name, String iban, String currency) {
        UUID uuid = UUID.randomUUID();
        var uuidString = uuid.toString();
        accounts.add(new Account(uuidString, name, iban, currency));
        return uuidString;
    }


}
