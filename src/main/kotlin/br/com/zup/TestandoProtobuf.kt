package br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.LocalDateTime

fun main() {
    val request = FuncionarioRequest.newBuilder()
        .setNome("Frederico Neres")
        .setCpf("000.000.000-00")
        .setCargo(Cargo.DEV)
        .setSalario(5000.00)
        .setAtivo(true)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setCep("89237-452")
            .setLogradouro("Rua Antônio Meras Sagas")
            .setComplemento("CASA")
            .build())
        .build()

    request.writeTo(FileOutputStream("funcionario-request.bin"))

    val request2 = FuncionarioRequest.newBuilder().mergeFrom(FileInputStream("funcionario-request.bin"))

    request2.setCargo(Cargo.GERENTE).build()

    println(request2)

}
