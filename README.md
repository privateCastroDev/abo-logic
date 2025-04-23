# Projeto TCC - Tipagem Sanguínea

## Descrição

Este projeto tem como objetivo desenvolver uma aplicação que calcule a tipagem sanguínea do filho de um casal com base nas tipagens sanguíneas dos pais. Utilizando **Java** e **Spring Boot** para o back-end, com **Docker** para containerização e **MySQL** para o banco de dados, o sistema também inclui um front-end interativo em **JavaScript** para interação com o usuário.

O sistema será capaz de:
- Determinar a tipagem sanguínea do filho a partir das informações dos pais.
- Validar a compatibilidade sanguínea para transfusão.
- Calcular a data prevista do parto com base no atraso menstrual informado.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento do back-end.
- **Spring Boot**: Framework utilizado para a criação da API RESTful que gerencia a lógica do sistema.
- **Docker**: Utilizado para criar ambientes de desenvolvimento isolados e para rodar o banco de dados MySQL em containers.
- **MySQL**: Banco de dados utilizado para armazenar informações dos pais e do filho, como tipagem sanguínea e dados relacionados à saúde.
- **DBeaver**: Ferramenta de administração de banco de dados utilizada para o gerenciamento e consulta ao banco de dados MySQL.
- **JavaScript**: Linguagem de programação utilizada para o desenvolvimento do front-end e interação com a API.

## Funcionalidades

- **Cadastro de Pais**: O usuário pode cadastrar informações sobre os pais, incluindo suas tipagens sanguíneas.
- **Cálculo da Tipagem Sanguínea do Filho**: Com base nas tipagens sanguíneas dos pais, o sistema calcula a tipagem sanguínea possível do filho.
- **Compatibilidade Sanguínea**: A aplicação determina a compatibilidade sanguínea entre os pais e o filho para transfusão.
- **Cálculo da Data Prevista de Parto**: Com a data da última menstruação da mãe, o sistema calcula a data prevista para o parto.
- **API RESTful**: A comunicação entre o front-end e o back-end é feita por meio de uma API RESTful criada com Spring Boot.

## Estrutura do Projeto

- **Backend**:
  - **Spring Boot** com endpoints REST para manipulação de dados.
  - Banco de dados **MySQL** para persistência dos dados.
  - **Docker** para rodar o banco de dados em um container.
  - Lógica de cálculo da tipagem sanguínea do filho com base nas tipagens dos pais.

- **Frontend**:
  - **JavaScript** para interação com a API e apresentação das informações.
  - Formulários de cadastro e visualização da tipagem sanguínea.
  - Cálculos de compatibilidade sanguínea e data prevista para o parto.

## Como Rodar o Projeto

### Pré-requisitos
- **Docker** instalado na máquina local.
- **Java 17** ou superior.
- **MySQL** rodando em Docker (via container).
- **DBeaver** para consulta e gerenciamento do banco de dados.

### Passo 1: Rodando o Banco de Dados

1. Clone o repositório para sua máquina local.
2. No diretório do projeto, execute o comando abaixo para subir o banco de dados MySQL em Docker:

   ```bash
   docker-compose up -d
   ```

3. O banco de dados estará rodando na porta `3306`. Você pode usar o **DBeaver** para conectar ao banco de dados e visualizar as tabelas.

### Passo 2: Rodando o Backend

1. No diretório do backend, compile e rode a aplicação Spring Boot:

   ```bash
   ./mvnw spring-boot:run
   ```

2. A API estará disponível em `http://localhost:8080`.

### Passo 3: Rodando o Frontend

1. No diretório do frontend, abra o arquivo HTML no seu navegador.
2. A interface permitirá o cadastro dos pais, visualização da tipagem sanguínea do filho e cálculos de compatibilidade.

## Endpoints da API

### `POST /api/pais/cadastrar`
Cadastra as tipagens sanguíneas dos pais.

**Request**:
```json
{
  "paiTipagem": "A",
  "maeTipagem": "B"
}
```

**Response**:
```json
{
  "filhoTipagem": "AB"
}
```

### `GET /api/pais/compatibilidade`
Verifica a compatibilidade sanguínea entre os pais e o filho.

**Response**:
```json
{
  "compatibilidade": "Compatível"
}
```

### `GET /api/pais/dataPrevistaParto`
Calcula a data prevista para o parto com base na data da última menstruação da mãe.

**Request**:
```json
{
  "ultimaMenstruacao": "2024-08-01"
}
```

**Response**:
```json
{
  "dataPrevistaParto": "2025-05-08"
}
```

## Como Contribuir

1. Faça um fork do projeto.
2. Crie uma branch para a sua funcionalidade (`git checkout -b minha-funcionalidade`).
3. Faça commit das suas mudanças (`git commit -am 'Adicionando nova funcionalidade'`).
4. Envie para o repositório remoto (`git push origin minha-funcionalidade`).
5. Abra um pull request.
