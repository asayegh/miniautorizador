package com.vr.miniautorizador.strategy;

import com.vr.miniautorizador.exception.input.InputErro;
import static com.vr.miniautorizador.util.Constants.SALDO_INSUFICIENTE;

public class SaldoInsuficienteStrategy implements Strategy {

    @Override
    public void apply() {
        throw new InputErro(SALDO_INSUFICIENTE);
    }
}