package com.vr.miniautorizador.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class TransacaoResponseDto {

    private BigDecimal valorTransacao;

}
