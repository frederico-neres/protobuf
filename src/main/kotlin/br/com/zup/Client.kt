package br.com.zup

import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)

    val request = FuncionarioRequest.newBuilder()
        .setNome("Frederico Neres")
        .setCpf("000.000.000-00")
        .setIdade(23)
        .setCargo(Cargo.DEV)
        .setSalario(5000.00)
        .setAtivo(true)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setCep("89237-452")
            .setLogradouro("Rua Antônio Meras Sagas")
            .setComplemento("CASA")
            .build())
        .build()

    val response = client.cadastrar(request)
    println("client $response")
}