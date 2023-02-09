package com.vr.miniautorizador.strategy;

import com.vr.miniautorizador.exception.transaction.ErroCustomizadoTransacao;
import static com.vr.miniautorizador.util.Constants.SENHA_INVALIDA;

public class SenhaIncorretaStrategy implements Strategy {

    @Override
    public void apply() {
        throw new ErroCustomizadoTransacao(SENHA_INVALIDA);
    }
}
