package com.vr.miniautorizador.builder;

import com.vr.miniautorizador.dto.CartaoRequestDto;

public class CartaoBuilder {

    public static CartaoRequestDto cartaoRequestDto() {

        return CartaoRequestDto.builder()
                .numeroCartao("1102003948572938")
                .senha("1234")
                .build();
    };

    public static CartaoRequestDto cartaoRequestDtoNumeroCartaoInvalido() {

        return CartaoRequestDto.builder()
                .numeroCartao("1102003948572938aa")
                .senha("1234")
                .build();
    };

    public static CartaoRequestDto cartaoRequestDtoSenhaInvalida() {

        return CartaoRequestDto.builder()
                .numeroCartao("1102003948572938")
                .senha("123412a")
                .build();
    };

}
