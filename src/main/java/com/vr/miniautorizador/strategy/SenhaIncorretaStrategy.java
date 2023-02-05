package com.vr.miniautorizador.strategy;

import org.springframework.beans.factory.annotation.Value;

public class SenhaIncorretaStrategy implements Strategy {

    private static String SENHA_INCORRETA;
    @Value("${constants.cartaoInexistente}")

    @Override
    public void apply() {
        //throw new CustomException();
    }
}
