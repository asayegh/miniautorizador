package com.vr.miniautorizador.exception.sql;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConexaoErro {

    private int codigo;
    private String mensagem;
    private LocalDateTime data;

}

