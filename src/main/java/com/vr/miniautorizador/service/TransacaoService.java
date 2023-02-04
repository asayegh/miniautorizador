package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.dto.TransacaoResponseDto;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.model.Transacao;
import com.vr.miniautorizador.repository.CartaoRepository;
import com.vr.miniautorizador.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    private static String throwException() {
        throw new RuntimeException("As strings são diferentes");
    }

    private String salvarDados(Optional<BigDecimal> valorTransacao, Optional<Cartao> cartao) {

        var transacaoResponse = new TransacaoResponseDto();
        transacaoResponse.setValorTransacao(valorTransacao.get());

        var transacao = new Transacao();
        transacao.setCartao(cartao.get());
        transacao.setValorTransacao(valorTransacao.get());

        cartao.get().setSaldo(cartao.get().getSaldo().subtract(valorTransacao.get()));

        cartaoRepository.save(cartao.get());
        transacaoRepository.save(transacao);

        return ("OK");
    }

    public String criarTransacao(TransacaoRequestDto transacaoRequestDto) {

        var numeroCartao = Optional.of(transacaoRequestDto.getNumeroCartao());
        var senhaCartao = Optional.of(transacaoRequestDto.getSenhaCartao());
        var valorTransacao = Optional.of(transacaoRequestDto.getValor());

        var cartao = cartaoRepository.findByNumeroCartao(numeroCartao.get());
        cartao.orElseThrow(() -> new RuntimeException("Cartão inexistente"));

        String result = senhaCartao.get().equals(cartao.get().getSenha()) ? "OK" : throwException();

        var saldo = cartao.get().getSaldo();

        result = (saldo.compareTo(valorTransacao.get()) == 1) ?
                salvarDados(valorTransacao, cartao) :
                throwException();

        return result;
    }
}
