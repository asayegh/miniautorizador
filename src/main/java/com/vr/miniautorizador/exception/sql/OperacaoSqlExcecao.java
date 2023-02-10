package com.vr.miniautorizador.exception.sql;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;

@ControllerAdvice
public class OperacaoSqlExcecao extends PersistenceException {

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<SqlRespostaDtoErro> handle(PersistenceException e) {

        var response = new SqlRespostaDtoErro().builder()
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensagem(((SQLGrammarException) e).getSQLException()
                        .getLocalizedMessage())
                .data(LocalDateTime.now())
                .build();

        return ResponseEntity.internalServerError().body(response);

    }
}
