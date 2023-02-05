package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.dto.CartaoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomErrorResponse {

    private CartaoResponseDto cartaoResponseDto;

}

