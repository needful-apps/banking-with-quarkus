package org.acme;

import io.quarkus.grpc.GrpcService;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

    private static final Logger LOG = Logger.getLogger(HelloGrpcService.class);

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        LOG.warn("Received request: " + request.getName());
        LOG.info("Hey there");
        String msg = "Hello " + request.getName() + "!";

        return Uni.createFrom().item(HelloReply.newBuilder().setMessage(msg).build());
    }

    @Override
    public Uni<HelloReply> sayGoodbye(HelloRequest request) {
        return Uni.createFrom().item("Goodbye " + request.getName() + "!")
                .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
    }


}
