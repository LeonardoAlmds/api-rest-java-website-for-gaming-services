# 🎮 K-PLUS API de Venda de Serviços

Este é um projeto de API REST desenvolvida em **Java com Spring Boot**, voltada para um site de venda de **serviços e moedas virtuais em jogos online**, no estilo da plataforma **GGMax**.

## ⚙️ Tecnologias Utilizadas

- **Java 21+**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)** para autenticação
- **Spring Data JPA (Hibernate)**
- **MySQL** como banco de dados
- **Swagger (SpringDoc OpenAPI)** para documentação interativa

---

## 🔐 Funcionalidades de Segurança

- Autenticação via **JWT**
- Controle de acesso por **nível de permissão**
- Proteção de rotas com **Spring Security**
- Rotas públicas e privadas configuradas

---

## 📦 Funcionalidades da API

- Cadastro e autenticação de usuários
- Controle de acesso com múltiplos **níveis de usuário** (ex: admin, vendedor, cliente)
- Cadastro de **produtos** e **subprodutos**
- Gerenciamento de **categorias**
- Criação e controle de **ordens de venda**
- Validação e organização de dados
- Documentação completa com Swagger

---

## 📑 Documentação

A API é totalmente documentada utilizando o **Swagger UI**.

> Acesse a documentação interativa em:  
> `http://localhost:8080/swagger-ui.html`  
> ou  
> `http://localhost:8080/swagger-ui/index.html`

---

## 🚀 Como executar o projeto

1. **Clone o repositório**
   ```bash
   git clone https://github.com/LeonardoAlmds/api-rest-java-website-for-gaming-services.git
   cd api-rest-java-website-for-gaming-services
