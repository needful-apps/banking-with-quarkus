package org.acme;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class TransactionGrpcService implements TransactionGrpc {

    @Inject
    TransactionService service;

    @Override
    public Uni<TransactionReply> addTransaction(TransactionRequest request) {
        String id = service.addTransaction(request.getSender(), request.getReceiver(), request.getAmountBc(), request.getAmountAc(), request.getCurrency());
        return Uni.createFrom().item(TransactionReply.newBuilder().setId(id).build());
    }

    @Override
    public Uni<TransactionReply> getTransaction(TransactionByIdRequest request) {
        var transaction = service.getTransactionById(request.getId());
        return Uni.createFrom().item(transaction.toTransactionReply());
    }

    @Override
    public Uni<TransactionListReply> getTransactions(TransactionListRequest request) {
        var transactions = service.getTransactions();
        List<TransactionReply> transactionReplies = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionReplies.add(transaction.toTransactionReply());
        }
        return Uni.createFrom().item(TransactionListReply.newBuilder().addAllTransactions(transactionReplies).build());
    }
}
