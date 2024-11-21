package org.acme;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class AccountGrpcService implements AccountGrpc {


    @Inject
    AccountService service;

    @Override
    public Uni<AccountReply> addAccount(AccountRequest request) {
        String id = service.addAccount(request.getName(), request.getIban(), request.getCurrency());
        return Uni.createFrom().item(AccountReply.newBuilder().setId(id).build());
    }

    @Override
    public Uni<AccountReply> getAccount(AccountByIdRequest request) {
        String id = request.getId();
        Account account = service.getAccountById(id);
        return Uni.createFrom().item(account.toAccountReply());
    }

    @Override
    public Uni<AccountListReply> getAccounts(AccountListRequest request) {
        List<Account> accounts = service.getAccounts();
        List<AccountReply> accountReplies = new ArrayList<>();
        for (Account account : accounts) {
            accountReplies.add(account.toAccountReply());
        }
        return Uni.createFrom().item(AccountListReply.newBuilder().addAllAccounts(accountReplies).build());
    }


}
