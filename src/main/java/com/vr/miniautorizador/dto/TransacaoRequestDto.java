package com.vr.miniautorizador.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@Data
public class TransacaoRequestDto {

    @Pattern(regexp = "^\\d{16}$")
    private String numeroCartao;

    @Pattern(regexp = "^\\d{4}$")
    private String senhaCartao;

    @DecimalMin(value = "0.01")
    private BigDecimal valor;
}
