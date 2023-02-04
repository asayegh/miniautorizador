package com.vr.miniautorizador.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.vr.miniautorizador.jackson.Views;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@JsonPropertyOrder({"senha", "numeroCartao"})
public class CartaoDto {

    @NotEmpty
    @JsonView({Views.Create.class, Views.Get.class})
    @Pattern(regexp = "^\\d{16}$")
    private String numeroCartao;

    @NotEmpty
    @JsonView({Views.Create.class, Views.Get.class})
    @Pattern(regexp = "^\\d{4}$")
    private String senha;

    @JsonView(Views.Get.class)
    private BigDecimal saldo;

}
