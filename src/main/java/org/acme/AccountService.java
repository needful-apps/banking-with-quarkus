package org.acme;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AccountService {

    @Inject
    TransactionService transactionService;

    List<Account> accounts = new ArrayList<>();

    public Account getAccountById(String id) {
        var item =  accounts.stream()
                .filter(account -> account.id.equals(id))
                .findFirst()
                .orElse(new Account("0", "Not found", "", ""));

        item.setBalance(getBalance(id));
        return item;
    }

    public String getBalance(String id) {
        var transactions = transactionService.getTransactions();
        BigDecimal balance = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            if (transaction.from.equals(id)) {
                balance = balance.subtract(transaction.amount);
            }
            if (transaction.to.equals(id)) {
                balance = balance.add(transaction.amount);
            }
        }
        return balance.toString();

    }

    public String addAccount(String name, String iban, String currency) {
        UUID uuid = UUID.randomUUID();
        var uuidString = uuid.toString();
        accounts.add(new Account(uuidString, name, iban, currency));
        return uuidString;
    }

    public List<Account> getAccounts() {
        return accounts;
    }


}
