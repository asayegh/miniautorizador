package com.vr.miniautorizador.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"senha", "numeroCartao"})
public class CartaoResponseDto {

    private String numeroCartao;
    private String senha;

}
