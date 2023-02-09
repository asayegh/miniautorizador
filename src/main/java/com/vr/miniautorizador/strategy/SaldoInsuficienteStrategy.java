package com.vr.miniautorizador.strategy;

import com.vr.miniautorizador.exception.transaction.ErroCustomizadoTransacao;
import static com.vr.miniautorizador.util.Constants.SALDO_INSUFICIENTE;

public class SaldoInsuficienteStrategy implements Strategy {

    @Override
    public void apply() {
        throw new ErroCustomizadoTransacao(SALDO_INSUFICIENTE);
    }
}