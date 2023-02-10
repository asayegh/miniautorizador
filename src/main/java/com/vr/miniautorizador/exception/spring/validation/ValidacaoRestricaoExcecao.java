package com.vr.miniautorizador.exception.spring.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ValidacaoRestricaoExcecao {

    private HttpStatus status;
    private List<String> erros;

    public ValidacaoRestricaoExcecao(HttpStatus status, List<String> errors) {

        super();
        this.status = status;
        this.erros = errors;
    }

    public ValidacaoRestricaoExcecao(HttpStatus status, String erro) {

        super();
        this.status = status;
        erros = Arrays.asList(erro);

    }
}
