package com.vr.miniautorizador.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ResponseMessage simpleMessage(int code, String message) {
        return new ResponseMessageBuilder().code(code).message(message).build();
    }

    private final ResponseMessage CODE_200 = simpleMessage(200, "Chamada realizada com successo");
    private final ResponseMessage CODE_201 = simpleMessage(201, "Recurso criado");
    private final ResponseMessage CODE_204 = simpleMessage(204, "Recurso atualizado");
    private final ResponseMessage CODE_401 = simpleMessage(401, "Autorização requerida");
    private final ResponseMessage CODE_403 = simpleMessage(403, "Não autorizado");
    private final ResponseMessage CODE_404 = simpleMessage(404, "Recurso não encontrado");
    private final ResponseMessage CODE_422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage CODE_500 = simpleMessage(500, "Erro inesperado");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(CODE_200, CODE_201, CODE_403, CODE_422, CODE_500))
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(CODE_403, CODE_404, CODE_500))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version("1.0")
                .title("Mini-Autorizador VR")
                .description("API para transaçôes financeiras com cartões")
                .build();
    }
}