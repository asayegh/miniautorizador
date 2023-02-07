# Desafio Técnico VR
#### Mini Autorizador - Descritivo e instruções para rodar o projeto

[![N|Solid](https://www.vr.com.br/lumis-theme/br/com/vr/portal/theme/vr-portal/img/svg/logo-vr.svg)](https://www.vr.com.br/)

## Frameworks/Tecnologias utilizadas
> - JAVA 11 (Oracle OpenJDK version 11.0.17)
> - Spring Boot 2.7.8
> - Docker Desktop 4.16.3
> - Banco de Dados MySQL 5.7
> - Maven 3.8.1
> - Swagger 2.9.2

## Descritivo
Instale o `Oracle OpenJDK version 11.0.17` e `Maven 3.8.1` (não testado em outras versões de JAVA 11 e Maven 3). No arquivo docker-compose.yml, comente ou exclua a seção relativa ao MongoDB. Execute o docker compose para obter o container do `MySQL 5.7`, e o inicie. Quando tudo estiver instalado e configurado, você poderá rodar o projeto e testes, clonando-o no seguinte endereço:

```sh
https://github.com/asayegh/miniautorizador.git
```
 
 Não foram criados arquivos de configuração para outros ambientes, há apenas um arquivo de configuração (`application.yml`), em:

```sh
src/main/resources/application.yml
```

## Documentação da API
A documentação (Swagger) encontra-se em:
- http://localhost:8080/swagger-ui.html

## Descrição de endpoints/métodos

### Cartão controller
|Método          |Path                           |Descrição                    |
|----------------|-------------------------------|-----------------------------|
|POST            |/cartoes                       |Cria um cartão               |
|GET             |/cartoes/{numeroCartao}        |Obtém saldo do cartão        |

### Transação controller
|Método          |Path                           |Descrição                    |
|----------------|-------------------------------|-----------------------------|
|POST            |/transacoes                    |Realiza uma transação         |
