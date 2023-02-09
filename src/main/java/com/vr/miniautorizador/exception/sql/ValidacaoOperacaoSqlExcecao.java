package com.vr.miniautorizador.exception.sql;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.vr.miniautorizador.util.Constants.ERRO_SQL;

@ControllerAdvice
public class ValidacaoOperacaoSqlExcecao extends RuntimeException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<OperacaoSqlErro> handle(RuntimeException e) {

        var operacaoSqlErro = new OperacaoSqlErro().builder()
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensagem(ERRO_SQL)
                .data(LocalDateTime.now())
                .build();

        return ResponseEntity.internalServerError().body(operacaoSqlErro);

    }
}
