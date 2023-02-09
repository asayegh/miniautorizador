package com.vr.miniautorizador.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidacaoNotFoundExcecao {
    @ExceptionHandler(ErroCustomizadoNotFoundResposta.class)
    public ResponseEntity<Void> handle(ErroCustomizadoNotFoundResposta ex) {
        return ResponseEntity.notFound().build();
    }
}
