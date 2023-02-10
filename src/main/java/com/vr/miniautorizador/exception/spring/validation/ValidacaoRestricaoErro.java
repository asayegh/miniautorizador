package com.vr.miniautorizador.exception.spring.validation;


import com.vr.miniautorizador.exception.spring.validation.ValidacaoRestricaoExcecao;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidacaoRestricaoErro extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField().concat(": ").concat(x.getDefaultMessage()))
                .collect(Collectors.toList());

        ValidacaoRestricaoExcecao validacaoRestricaoExcecao = new ValidacaoRestricaoExcecao(status, errors);

        return new ResponseEntity<>(validacaoRestricaoExcecao, validacaoRestricaoExcecao.getStatus());
    }

}