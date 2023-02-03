package com.vr.miniautorizador.util;

public interface FunctionalInterface<T, U> {
    boolean validar(T objeto, U comparavel);

}
