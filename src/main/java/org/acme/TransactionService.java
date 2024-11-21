package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TransactionService {
    List<Transaction> transactions = new ArrayList<>();

    public Transaction getTransactionById(String id) {
        return transactions.stream()
                .filter(transaction -> transaction.id.equals(id))
                .findFirst()
                .orElse(new Transaction());
    }

    public String addTransaction(String from, String to, Long left, Long right, String currency) {
        BigDecimal amount = new BigDecimal(left + "." + right);
        Transaction transaction = new Transaction();
        transaction.id = java.util.UUID.randomUUID().toString();
        transaction.from = from;
        transaction.to = to;
        transaction.amount = amount;
        transaction.currency = currency;
        transactions.add(transaction);
        return transaction.id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
