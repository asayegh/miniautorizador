package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.dto.TransacaoResponseDto;
import com.vr.miniautorizador.exception.sql.OperacaoSqlExcecao;
import com.vr.miniautorizador.exception.input.InputErro;
import com.vr.miniautorizador.model.Transacao;
import com.vr.miniautorizador.repository.CartaoRepository;
import com.vr.miniautorizador.repository.TransacaoRepository;
import com.vr.miniautorizador.strategy.Strategy;
import com.vr.miniautorizador.strategy.StrategyConditionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;
import java.util.Optional;

import static com.vr.miniautorizador.util.Constants.*;

@Service
public class TransacaoService {

    @Value("${spring.messages.ok}")
    private String OK;

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    private final StrategyConditionFactory strategyConditionFactory = new StrategyConditionFactory();

    public void decide(String someCondition) {
        String s = someCondition;
        Strategy strategy = strategyConditionFactory.getStrategy(someCondition)
                .orElseThrow(() -> new IllegalArgumentException(CONDICAO_INVALIDA));
        strategy.apply();
    }

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public String criarTransacao(TransacaoRequestDto transacaoRequestDto) {

        var numeroCartao = Optional.of(transacaoRequestDto.getNumeroCartao());
        var senhaCartao = Optional.of(transacaoRequestDto.getSenhaCartao());
        var valorTransacaoRequest = Optional.of(transacaoRequestDto.getValor());

        var cartao = cartaoRepository.findByNumeroCartao(numeroCartao.get());
        cartao.orElseThrow(() -> new InputErro(CARTAO_INEXISTENTE));

        var comparaSenha = senhaCartao.get().equals(cartao.get().getSenha()) ?
                SENHA_VALIDA : SENHA_INVALIDA;
        decide(comparaSenha);
        var saldo = cartao.get().getSaldo();
        var comparaSaldo = saldo.compareTo(valorTransacaoRequest.get()) >= 0 ?
                SALDO_SUFICIENTE : SALDO_INSUFICIENTE;
        decide(comparaSaldo);

        var transacaoResponse = new TransacaoResponseDto();
        transacaoResponse.setValorTransacao(valorTransacaoRequest.get());

        var transacao = new Transacao();
        transacao.setCartao(cartao.get());
        transacao.setValorTransacao(valorTransacaoRequest.get());
        try {
            transacaoRepository.save(transacao);
            cartao.get().setSaldo(cartao.get().getSaldo().subtract(valorTransacaoRequest.get()));
            cartaoRepository.save(cartao.get());
        } catch (PersistenceException e) {
            throw new OperacaoSqlExcecao();
        }

        return OK;

    }
}
