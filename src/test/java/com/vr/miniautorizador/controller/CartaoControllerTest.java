package com.vr.miniautorizador.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.miniautorizador.dto.CartaoRequestDto;
import com.vr.miniautorizador.exception.ValidacaoCriarCartao;
import com.vr.miniautorizador.service.CartaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("staging")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CartaoControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartaoService cartaoService;

    @Test
    void deveCriarUmCartao() throws Exception {

        CartaoRequestDto cartao = CartaoRequestDto.builder()
                .numeroCartao("1102003948572938")
                .senha("1234")
                .build();
        mockMvc.perform(post("/cartoes").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated());
    }

    @Test
    void cartaoDuplicado() throws Exception {

        CartaoRequestDto cartao = CartaoRequestDto.builder()
                .numeroCartao("1102003948572938")
                .senha("1234")
                .build();

        when(cartaoService.criarCartao(cartao)).thenThrow(ValidacaoCriarCartao.class);

        mockMvc.perform(post("/cartoes").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartao)))
                .andExpect(status().isUnprocessableEntity());
    }
}
