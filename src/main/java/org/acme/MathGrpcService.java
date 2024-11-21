package org.acme;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class MathGrpcService implements MathGrpc{
    @Override
    public Uni<MathReply> add(MathRequest request) {
        int result = request.getA() + request.getB();

        var r = Uni.createFrom()
                .item(MathReply.newBuilder()
                        .setResult(Integer.toString(result))
                        .build());
        return r;
    }



    @Override
    public Uni<MathReply> subtract(MathRequest request) {
        int result = request.getA() - request.getB();

        return Uni.createFrom()
                .item(MathReply.newBuilder()
                        .setResult(Integer.toString(result))
                        .build());
    }
}
