package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.CartaoRequestDto;
import com.vr.miniautorizador.dto.CartaoResponseDto;
import com.vr.miniautorizador.exception.rule.CartaoImprocessavelErro;
import com.vr.miniautorizador.exception.rule.RecursoNaoEncontradoErro;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.Optional;


@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public CartaoResponseDto criarCartao(CartaoRequestDto cartaoRequestDto) {

            var mapper = new ModelMapper();
            var cartao = new Cartao();
            var request = mapper.map(cartaoRequestDto, Cartao.class);

            try {
                cartao = cartaoRepository.save(request);
            } catch (DataIntegrityViolationException e) {
                var cartaoResponseDto = CartaoResponseDto.builder()
                        .numeroCartao(cartaoRequestDto.getNumeroCartao())
                        .senha(cartaoRequestDto.getSenha())
                        .build();
                throw new CartaoImprocessavelErro(cartaoResponseDto);
            }

            var response = mapper.map(cartao, CartaoResponseDto.class);
            return response;
    }

    public BigDecimal buscaCartaoPorNumero(String numeroCartao) {

        Optional<Cartao> cartao = cartaoRepository.findByNumeroCartao(numeroCartao);
        cartao.orElseThrow(
                () -> new RecursoNaoEncontradoErro("Recurso não encontrado")
        );
        return cartao.get().getSaldo();
    }

}
