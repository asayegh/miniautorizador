package com.vr.miniautorizador.exception.sql;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperacaoSqlErro {

    private int codigo;
    private String mensagem;
    private LocalDateTime data;
}
