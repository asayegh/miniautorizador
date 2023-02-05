package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.dto.CartaoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Getter
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidacaoCriarCartao extends RuntimeException {

    private CartaoResponseDto cartaoResponseDto;

}

