package com.vr.miniautorizador.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class TransacaoRequestDto {

    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;

}
