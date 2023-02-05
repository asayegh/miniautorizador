package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.dto.TransacaoResponseDto;
import com.vr.miniautorizador.model.Transacao;
import com.vr.miniautorizador.repository.CartaoRepository;
import com.vr.miniautorizador.repository.TransacaoRepository;
import com.vr.miniautorizador.strategy.Strategy;
import com.vr.miniautorizador.strategy.StrategyConditionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService {

    @Value("${spring.messages.saldoInsuficiente}")
    private String SALDO_INSUFICIENTE;
    @Value("${spring.messages.senhaIncorreta}")
    private String SENHA_INCORRETA;
    @Value("${spring.messages.cartaoInvalido}")
    private String CARTAO_INEXISTENTE;
    @Value("${spring.messages.ok}")
    private String OK;

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    CartaoRepository cartaoRepository;


    private final StrategyConditionFactory strategyConditionFactory = new StrategyConditionFactory();

    public void decide(String someCondition) {
        Strategy strategy = strategyConditionFactory.getStrategy(someCondition)
                .orElseThrow(() -> new IllegalArgumentException("Wrong condition"));
        strategy.apply();
    }

    private static boolean throwException(String message) {
        throw new RuntimeException(message);
    }

    public String criarTransacao(TransacaoRequestDto transacaoRequestDto) {

        var numeroCartao = Optional.of(transacaoRequestDto.getNumeroCartao());
        var senhaCartao = Optional.of(transacaoRequestDto.getSenhaCartao());
        var valorTransacaoRequest = Optional.of(transacaoRequestDto.getValor());

        var cartao = cartaoRepository.findByNumeroCartao(numeroCartao.get());
        cartao.orElseThrow(() -> new RuntimeException(CARTAO_INEXISTENTE));

        var comparaSenha = senhaCartao.get().equals(cartao.get().getSenha()) ? OK : SENHA_INCORRETA;
        decide(comparaSenha);

        var saldo = cartao.get().getSaldo();

        var comparaSaldo = saldo.compareTo(valorTransacaoRequest.get()) == 1 ? OK : SALDO_INSUFICIENTE;
        decide(comparaSaldo);

        var transacaoResponse = new TransacaoResponseDto();
        transacaoResponse.setValorTransacao(valorTransacaoRequest.get());

        var transacao = new Transacao();
        transacao.setCartao(cartao.get());
        transacao.setValorTransacao(valorTransacaoRequest.get());

        cartao.get().setSaldo(cartao.get().getSaldo().subtract(valorTransacaoRequest.get()));

        cartaoRepository.save(cartao.get());
        transacaoRepository.save(transacao);

        return OK;
    }
}
