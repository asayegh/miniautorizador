package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.CartaoRequestDto;
import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.exception.input.InputErro;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;
import com.vr.miniautorizador.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoServiceTest {

    @Value("${spring.messages.ok}")
    private String OK;

    @Autowired
    private TransacaoService transacaoService;

    @MockBean
    private CartaoRepository cartaoRepository;

    @MockBean
    private TransacaoRepository transacaoRepository;

    private ModelMapper mapper = new ModelMapper();

    @Test
    void testeQuandoTodosOsDadosValidoExecutarTransacao() {

        var transacaoRequestDto =  TransacaoRequestDto.builder()
                .numeroCartao("9920192883456739")
                .senhaCartao("1234")
                .valor(new BigDecimal("150.00"))
                .build();

        var cartaoRequestDto = CartaoRequestDto.builder()
                .numeroCartao(transacaoRequestDto.getNumeroCartao())
                .senha(transacaoRequestDto.getSenhaCartao())
                .saldo(new BigDecimal("500.00"))
                .build();

        var cartao = mapper.map(cartaoRequestDto, Cartao.class);

        when(cartaoRepository.findByNumeroCartao(transacaoRequestDto.getNumeroCartao()))
                .thenReturn(Optional.of(cartao));

        var response = transacaoService.criarTransacao(transacaoRequestDto);

       assertEquals(response, OK);
    }

    @Test
    void testeSenhaInvalidaExecutarTransacaRetornaExcecao() {

        var transacaoRequestDto =  TransacaoRequestDto.builder()
                .numeroCartao("9920192883456739")
                .senhaCartao("1234")
                .valor(new BigDecimal("150.00"))
                .build();

        var cartaoRequestDto = CartaoRequestDto.builder()
                .numeroCartao(transacaoRequestDto.getNumeroCartao())
                .senha(transacaoRequestDto.getSenhaCartao())
                .saldo(new BigDecimal("500.00"))
                .build();

        var cartao = mapper.map(cartaoRequestDto, Cartao.class);

        when(cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao())).thenThrow(InputErro.class);

        assertThrows(InputErro.class,
                () -> transacaoService.criarTransacao(transacaoRequestDto));
    }
}
