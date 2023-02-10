package com.vr.miniautorizador.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.miniautorizador.builder.CartaoBuilder;
import com.vr.miniautorizador.exception.rule.RecursoNaoEncontradoErro;
import com.vr.miniautorizador.exception.rule.CartaoImprocessavelErro;
import com.vr.miniautorizador.service.CartaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.ConstraintViolationException;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartaoControllerTest {

    private static final String URL = "/cartoes";
    private static final String PATH_CARTAO = "/{numeroCartao}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartaoService cartaoService;

    //POST
    @Test
    void testeCriarCartaoValidoRetornaHttpStatusCode201() throws Exception {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDto();

        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testePostarCartaoDuplicadoGeraExcecao() throws Exception {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDto();

        when(cartaoService.criarCartao(cartaoRequestDto)).thenThrow(CartaoImprocessavelErro.class);

        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoRequestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testePostarCartaoInvalidoGeraExcecao() throws Exception {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDtoNumeroCartaoInvalido();

        when(cartaoService.criarCartao(cartaoRequestDto)).thenThrow(ConstraintViolationException.class);

        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testePostarSenhaInvalidaGeraExcecao() throws Exception {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDtoSenhaInvalida();

        when(cartaoService.criarCartao(cartaoRequestDto)).thenThrow(ConstraintViolationException.class);

        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoRequestDto)))
                .andExpect(status().isBadRequest());
    }

    //GET
    @Test
    void testeObterCartaoPeloNumeroCorretoRetornaHttpStatusCode200() throws Exception {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDto();

        this.mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoRequestDto))
                        .accept(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + PATH_CARTAO, "1102003948572938"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testeObterCartaoNumeroCorretoSenhaIncorretaRetornaHttpStatusCode404() throws Exception {

        var cartaoRequestDto = CartaoBuilder.cartaoRequestDto();

        when(cartaoService.buscaCartaoPorNumero("1234567890123456"))
                .thenThrow(RecursoNaoEncontradoErro.class);

        mockMvc.perform(MockMvcRequestBuilders.get(URL + PATH_CARTAO, "1234567890123456")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
