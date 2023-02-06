package com.vr.miniautorizador.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.exception.ErroCustomizadoTransacao;
import com.vr.miniautorizador.repository.CartaoRepository;
import com.vr.miniautorizador.repository.TransacaoRepository;
import com.vr.miniautorizador.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {

    private static final String URL = "/transacoes";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransacaoService transacaoService;

    @MockBean
    private TransacaoRepository transacaoRepository;

    @MockBean
    private CartaoRepository cartaoRepository;

    //POST
    @Test
    void testeCriarTransacaoRetornaHttpStatusCode201() throws Exception {

        var transacaoRequestDto =  TransacaoRequestDto.builder()
                .numeroCartao("9920192883456739")
                .senhaCartao("1234")
                .valor(new BigDecimal("150.00"))
                .build();

        when(transacaoService.criarTransacao(transacaoRequestDto)).thenReturn("OK");

        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestDto)))
                .andExpect(status().isCreated()).andExpect(content().string("OK"));
    }

    @Test
    void testeCriarTransacaoRetornaCartaoInexistenteHttpStatus422() throws Exception {

        var transacaoRequestDto =  TransacaoRequestDto.builder()
                .numeroCartao("9920192883456739")
                .senhaCartao("1234")
                .valor(new BigDecimal("150.00"))
                .build();

        when(transacaoService.criarTransacao(transacaoRequestDto)).thenThrow(ErroCustomizadoTransacao.class);

        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoRequestDto)))
                .andExpect(status().isUnprocessableEntity());
    }
}
