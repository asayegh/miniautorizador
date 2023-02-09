package com.vr.miniautorizador.service;

import com.vr.miniautorizador.builder.CartaoBuilder;
import com.vr.miniautorizador.exception.ResourceNotFoundException;
import com.vr.miniautorizador.exception.validation.ValidacaoCriarCartaoErroImprocessavel;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CartaoServiceTest {

    @Autowired
    private CartaoService cartaoService;

    @MockBean
    private CartaoRepository cartaoRepository;

    private ModelMapper mapper = new ModelMapper();


    @Test
    void testeQuandoCartaoValidoSalvarCartao() {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDto();
        var cartao = mapper.map(cartaoRequestDto, Cartao.class);

        when(cartaoRepository.save(any(Cartao.class))).thenReturn(cartao);

        var cartaoResponseDto = cartaoService.criarCartao(cartaoRequestDto);

        Assertions.assertNotNull(cartaoResponseDto);
        Assertions.assertEquals(cartaoResponseDto.getNumeroCartao(),
                cartaoRequestDto.getNumeroCartao());
    }

    @Test
    void testeQuandoCartaoInvalidoValidacaoCriarCartao() {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDto();

        when(cartaoRepository.save(any(Cartao.class))).thenThrow(ValidacaoCriarCartaoErroImprocessavel.class);

        assertThrows(ValidacaoCriarCartaoErroImprocessavel.class,
                () -> cartaoService.criarCartao(cartaoRequestDto));

    }

    @Test
    void testeQuandoBuscarPorNumeroRetornaSaldo() {

        String numeroCartao = "2993029182923357";
        var cartao = new Cartao();
        cartao.setNumeroCartao(numeroCartao);
        cartao.setSaldo(new BigDecimal("450.00"));

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.of(cartao));
        var saldo = cartaoService.buscaCartaoPorNumero(numeroCartao);

        assertThat(saldo, Matchers.comparesEqualTo(new BigDecimal("450.00")));
    }

    @Test
    void testeQuandoBuscarCartaoInvalidoParaObterSaldo() {

        String numeroCartao = "2993029182923357";

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class,
                () -> cartaoService.buscaCartaoPorNumero(numeroCartao));

    }
}
