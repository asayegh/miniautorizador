package com.vr.miniautorizador.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.miniautorizador.dto.CartaoDto;
import com.vr.miniautorizador.jackson.Views;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public CartaoDto criarCartao(CartaoDto cartaoDto) {

        var objectMapper = new ObjectMapper();
        var request = objectMapper.convertValue(cartaoDto, new TypeReference<Cartao>(){});
        var cartao = cartaoRepository.save(request);

        objectMapper.setConfig(objectMapper.getSerializationConfig()
                .withView(Views.class));

        var response = objectMapper.convertValue(cartao, new TypeReference<CartaoDto>() {});

        return response;

    }

    public BigDecimal buscaCartaoPorNumero(String numeroCartao ) {

        Optional<Cartao> cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        return Optional.ofNullable(cartao.get().getSaldo())
                .orElseThrow(() -> new RuntimeException());
    }

}
