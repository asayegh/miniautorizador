package com.vr.miniautorizador.strategy;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class StrategyConditionFactory {

    @Value("${constants.saldoSuficiente}")
    private static String SALDO_SUFICIENTE;
    @Value("${constants.saldoInsuficiente}")
    private static String SALDO_INSUFICIENTE;
    @Value("${constants.senhaCorreta}")
    private static String SENHA_CORRETA;
    @Value("${constants.senhaIncorreta}")
    private static String SENHA_INCORRETA;
    @Value("${constants.cartaoInexistente}")


    private Map<String, Strategy> conditions = new HashMap<>();

    public StrategyConditionFactory() {

        conditions.put(SENHA_CORRETA, new SenhaCorretaStrategy());
        conditions.put(SENHA_INCORRETA, new SenhaIncorretaStrategy());
        conditions.put(SALDO_SUFICIENTE, new SaldoSuficienteStrategy());
        conditions.put(SALDO_INSUFICIENTE, new SaldoInsuficienteStrategy());

    }

    public Optional<Strategy> getStrategy(String condition) {
        return Optional.ofNullable(conditions.get(condition));
    }
}
