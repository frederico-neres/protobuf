package br.com.zup

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {
    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioEndpoint())
        .build()

    server.start()
    server.awaitTermination()
}


class FuncionarioEndpoint : FuncionarioServiceGrpc.FuncionarioServiceImplBase() {
    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        println("server $request")

        val nome = if(request?.nome?.isEmpty() == true) "[???]" else request?.nome
        val criadoEm = LocalDateTime.now().toGoogleTimestamp()

        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}


fun LocalDateTime.toGoogleTimestamp(): Timestamp? {
    return this.atZone(ZoneId.of("UTC")).toInstant().run {
        Timestamp.newBuilder().setSeconds(this.epochSecond).setNanos(this.nano).build()
    }
}