package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.dto.TransacaoResponseDto;
import com.vr.miniautorizador.model.Transacao;
import com.vr.miniautorizador.repository.CartaoRepository;
import com.vr.miniautorizador.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    public String criarTransacao(TransacaoRequestDto transacaoRequestDto) {

        var numeroCartao = Optional.of(transacaoRequestDto.getNumeroCartao());
        var senhaCartao = Optional.of(transacaoRequestDto.getSenhaCartao());
        var valorTransacao = Optional.of(transacaoRequestDto.getValor());

        var cartao = cartaoRepository.findByNumeroCartao(numeroCartao.get());
        cartao.orElseThrow(() -> new RuntimeException("Cartão inexistente"));

        if (!senhaCartao.get().equals(cartao.get().getSenha()))
            throw new RuntimeException("Senha inválida");

        var saldo = cartao.get().getSaldo();

        if (saldo.compareTo(valorTransacao.get()) == 1) {

            var transacaoResponse = new TransacaoResponseDto();
            transacaoResponse.setValorTransacao(valorTransacao.get());

            var transacao = new Transacao();
            transacao.setCartao(cartao.get());
            transacao.setValorTransacao(valorTransacao.get());

            cartao.get().setSaldo(cartao.get().getSaldo().subtract(valorTransacao.get()));

            cartaoRepository.save(cartao.get());
            transacaoRepository.save(transacao);

        } else
            throw new RuntimeException("Saldo insuficiente");

        return "OK";
    }
}
