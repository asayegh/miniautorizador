package com.vr.miniautorizador.exception.rule;

import com.vr.miniautorizador.exception.rule.RecursoNaoEncontradoErro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecursoNaoEncontradoExcecao {
    @ExceptionHandler(RecursoNaoEncontradoErro.class)
    public ResponseEntity<Void> handle(RecursoNaoEncontradoErro ex) {
        return ResponseEntity.notFound().build();
    }
}
