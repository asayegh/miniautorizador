package com.vr.miniautorizador.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Data
public class TransacaoRequestDto {

    @Pattern(regexp = "^\\d{16}$", message = "Número de cartão inválido")
    private String numeroCartao;

    @Pattern(regexp = "^\\d{4}$", message = "Senha inválida")
    private String senhaCartao;

    @DecimalMin(value = "0.01", message = "O valor da transação precisa ser igual ou maior que 1 centavo")
    private BigDecimal valor;
}
