package com.vr.miniautorizador.strategy;

import org.springframework.beans.factory.annotation.Value;

public class SaldoInsuficienteStrategy implements Strategy {

    @Value("${constants.saldoInsuficiente}")
    private static String SALDO_INSUFICIENTE;

    @Override
    public void apply() {
        throw new RuntimeException(SALDO_INSUFICIENTE);
    }
}