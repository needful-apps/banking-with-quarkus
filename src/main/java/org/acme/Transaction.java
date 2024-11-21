package org.acme;

import java.math.BigDecimal;

public class Transaction {
    String id;
    String from;
    String to;
    BigDecimal amount;
    String currency;

    public String getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }



    public Transaction() {
    }

    public Transaction(String id, String from, String to, BigDecimal amount, String currency) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.currency = currency;
    }

    public Transaction(String from, String to, BigDecimal amount, String currency) {
        this.id = java.util.UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.currency = currency;
    }

    public TransactionReply toTransactionReply() {
        return TransactionReply.newBuilder()
                .setId(id)
                .setSender(from)
                .setReceiver(to)
                .setAmountBc(amount.longValue())
                .setAmountAc(amount.remainder(BigDecimal.ONE).movePointRight(amount.scale()).longValue())
                .setCurrency(currency)
                .build();
    }
}
