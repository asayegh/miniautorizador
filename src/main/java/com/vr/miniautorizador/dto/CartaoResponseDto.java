package com.vr.miniautorizador.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.vr.miniautorizador.jackson.Views;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Data
@Builder
@JsonPropertyOrder({"senha", "numeroCartao"})
public class CartaoResponseDto {

    private String numeroCartao;
    private String senha;
}
