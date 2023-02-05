package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.CartaoRequestDto;
import com.vr.miniautorizador.dto.CartaoResponseDto;
import com.vr.miniautorizador.exception.ResourceNotFoundException;
import com.vr.miniautorizador.exception.ValidacaoCriarCartao;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public CartaoResponseDto criarCartao(CartaoRequestDto cartaoRequestDto) {

        var mapper = new ModelMapper();

        var cartao = new Cartao();
        var request = mapper.map(cartaoRequestDto, Cartao.class);

        try {
            cartao = cartaoRepository.save(request);
        } catch(Exception e){
            var cartaoResponseDto = CartaoResponseDto.builder()
                    .numeroCartao(cartaoRequestDto.getNumeroCartao())
                    .senha(cartaoRequestDto.getSenha())
                    .build();
            throw new ValidacaoCriarCartao(cartaoResponseDto);
        }

        var response = mapper.map(cartao, CartaoResponseDto.class);
        return response;

    }

    public BigDecimal buscaCartaoPorNumero(String numeroCartao) {

        Optional<Cartao> cartao = cartaoRepository.findByNumeroCartao(numeroCartao);
        cartao.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return cartao.get().getSaldo();
    }

}
