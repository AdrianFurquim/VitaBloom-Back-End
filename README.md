# Vita Bloom Back-End

<h1 align="center">Vita Bloom Back-End</h1>

<div align="center">
  <img src="https://github.com/AdrianFurquim/VitaBloom-Back-End/assets/116688048/0b5a8582-c31e-45bb-93ea-67ed58191def" alt="logo Vita Bloom" />
</div>

Bem-vindo ao Back-End do site Vita Bloom! Abaixo estão detalhes sobre a estrutura do projeto e como iniciar a aplicação.

## Tecnologias Utilizadas

### Back-End
- **Java**
- **Spring Boot**
- **Spring Security**
- **CRUD**
- **MVC (Model, Controller, View)**
- **Maven**
- **MySQL / PostgreSQL**

<h4 align="center">:construction: Projeto Em Andamento :construction:</h4>
<p align="center">O projeto ainda está em andamento e sendo construído aos poucos com seu Front-End disponível no repositório: [Vita Bloom Front-End](https://github.com/AdrianFurquim/VitaBloom-Front-End.git).</p>

## Iniciando o Projeto

### Passo 1: Configuração do Ambiente

1. **Ferramentas Necessárias:**
   - IDE com suporte a Java (ex: IntelliJ, Eclipse, Visual Studio Code com extensão Thunder Client).
   - MySQL ou PostgreSQL para o banco de dados (pode ser configurado usando XAMPP ou outra ferramenta).

2. **Configuração do Banco de Dados:**
   - Ative seu servidor MySQL ou PostgreSQL.
   - Crie um novo esquema chamado `vita_bloom`.

3. **Configuração do Projeto:**
   - Clone este repositório.
   - Abra o projeto em sua IDE de preferência.
   - Edite o arquivo `application.properties` localizado em `/src/main/resources/application.properties` para configurar seu nome de usuário e senha de acordo com seu banco de dados:
     ```properties
     spring.datasource.username=seu_username
     spring.datasource.password=sua_senha
     ```

4. **Importação do Banco de Dados:**
   - Importe o banco de dados disponível junto ao projeto.

5. **Iniciar a Aplicação:**
   - Execute a aplicação Java para iniciar o servidor.

## Como Funciona?

O projeto funciona como uma API consumível desenvolvida em Java com o framework Spring Boot. O sistema possui diversas URLs para fazer requisições ao banco de dados, como GET, POST, DELETE e PUT.

### Estrutura do Banco de Dados

O banco de dados foi criado usando MySQL/PostgreSQL e possui as seguintes tabelas principais:
- **Usuário:** Cada usuário possui um carrinho.
- **Carrinho:** Cada carrinho contém um array de itens.
- **Item:** Cada item no carrinho é composto por um produto e a quantidade do mesmo.

Para uma melhor experiência, siga para o próximo passo no front-end: [Vita Bloom Front-End](https://github.com/AdrianFurquim/VitaBloom-Front-End.git).

## Equipe

- **Adrian Eduardo Furquim de Souza**
