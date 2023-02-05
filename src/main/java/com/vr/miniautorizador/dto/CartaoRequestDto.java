package com.vr.miniautorizador.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@JsonPropertyOrder({"senha", "numeroCartao"})
public class CartaoRequestDto {

    @NotEmpty
    @Pattern(regexp = "^\\d{16}$", message = "Número de cartão inválido")
    private String numeroCartao;

    @NotEmpty
    @Pattern(regexp = "^\\d{4}$", message = "Senha inválida")
    private String senha;

    @DecimalMin(value = "0.01", message = "O valor da transação precisa ser igual ou maior que 1 centavo")
    private BigDecimal saldo;

}
