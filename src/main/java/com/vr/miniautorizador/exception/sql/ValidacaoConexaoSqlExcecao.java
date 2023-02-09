package com.vr.miniautorizador.exception.sql;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.time.LocalDateTime;

import static com.vr.miniautorizador.util.Constants.ERRO_CONEXAO_BANCO;

@ControllerAdvice
public class ValidacaoConexaoSqlExcecao {

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErroCustomizadoSqlResposta> handleConnectException(ConnectException ex) {

        var conexaoErro = new ErroCustomizadoSqlResposta().builder()
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensagem(ERRO_CONEXAO_BANCO)
                .data(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(conexaoErro, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}