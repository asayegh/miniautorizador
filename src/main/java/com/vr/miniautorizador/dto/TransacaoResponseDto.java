package com.vr.miniautorizador.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
@Data
public class TransacaoResponseDto {

    private BigDecimal valorTransacao;

}
