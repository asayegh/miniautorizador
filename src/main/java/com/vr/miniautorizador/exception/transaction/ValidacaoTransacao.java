package com.vr.miniautorizador.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidacaoTransacao {

    @ExceptionHandler(ErroCustomizadoTransacaoResposta.class)
    public ResponseEntity<String> handle(ErroCustomizadoTransacaoResposta ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ex.getMessage());
    }
}