# OMD Teste - Sistema Plano de ação - backend

Sistema para gerenciamento de **Planos de Ação**, composto por:

- 🔹 Entidade `ActionPlan`: Plano de ação com título, objetivo, data de criação, status e lista de ações.
- 🔸 Entidade `Action`: Ações associadas a um Plano de Ação, contendo título, status e data de vencimento.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (banco de dados em memória)
- Maven
- Lombok
- Docker

## Executando o projeto localmente
### Pré-requisitos
- Docker

### Build da imagem docker
```shell
docker build -t omd-plano-de-acao .
```

### Executando o container
```shell
docker run -p 8080:8080 omd-plano-de-acao
```

### Acesse em:
http://localhost:8080

## Acesso ao Banco de Dados H2 (ambiente de desenvolvimento)
URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:omd-plano-de-acao-db

Usuário: sa

Senha: (em branco)

## Documentação da API
A documentação da API pode ser acessada em:
http://localhost:8080/swagger-ui/index.html

## Desenvolvido por
Ivan Carlos dos Santos

ivancsantos@hotmail.com.br

ivansantos.dev
