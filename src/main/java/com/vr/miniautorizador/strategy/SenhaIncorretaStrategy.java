package com.vr.miniautorizador.strategy;

import com.vr.miniautorizador.exception.input.InputErro;
import static com.vr.miniautorizador.util.Constants.SENHA_INVALIDA;

public class SenhaIncorretaStrategy implements Strategy {

    @Override
    public void apply() {
        throw new InputErro(SENHA_INVALIDA);
    }
}
