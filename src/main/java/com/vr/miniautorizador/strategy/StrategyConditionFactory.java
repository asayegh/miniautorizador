package com.vr.miniautorizador.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.vr.miniautorizador.util.Constants.*;


public class StrategyConditionFactory {

    private Map<String, Strategy> conditions = new HashMap<>();

    public StrategyConditionFactory() {

        conditions.put(SENHA_VALIDA, new SenhaCorretaStrategy());
        conditions.put(SENHA_INVALIDA, new SenhaIncorretaStrategy());
        conditions.put(SALDO_SUFICIENTE, new SaldoSuficienteStrategy());
        conditions.put(SALDO_INSUFICIENTE, new SaldoInsuficienteStrategy());

    }

    public Optional<Strategy> getStrategy(String condition) {
        return Optional.ofNullable(conditions.get(condition));
    }
}
