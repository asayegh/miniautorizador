package com.vr.miniautorizador.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Configuração de CORS. allowedOrigins("*") permite o acesso de qualquer URI
        //  Os exemplos abaixo são somente ilustrativos

        // Liberando acesso e métodos para usuário 1. Ele pode acessar qualquer endpoint da aplicação
        // e qualquer método
        /*registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST");*/

        // Liberando acesso e métodos para usuário 2. Ele pode xecutar somente o método POST do endpoint
        // transacoes
        /*registry.addMapping("/transacoes/**")
                .allowedOrigins("http://localhost:4000")
                .allowedMethods("POST");*/
    }
}