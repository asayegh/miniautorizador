package com.vr.miniautorizador.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

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
