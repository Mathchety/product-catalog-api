# Product Catalog API / API de Catálogo de Produtos

## 🇧🇷 Português

### 📝 Sobre o Projeto
Este é o meu primeiro CRUD completo desenvolvido utilizando **Java** com o framework **Spring Boot** e banco de dados **PostgreSQL**. O objetivo principal foi ir além do básico, aplicando padrões de mercado para construir uma API Rest funcional, segura, resiliente e performática.

### 🚀 Funcionalidades e Boas Práticas
*   **CRUD Completo:** Criação, listagem, atualização e deleção de produtos e categorias.
*   **Relacionamento entre Entidades:** Associação robusta entre produtos e suas respectivas categorias.
*   **Regras de Negócio Validadas:** Bloqueio de cadastros duplicados (nomes únicos para produtos e categorias) e checagem de existência de chaves estrangeiras.
*   **Padrão DTO (Data Transfer Object):** Implementado para garantir o desacoplamento das entidades do banco de dados e maior segurança na trafegabilidade dos dados.
*   **Paginação e Ordenação (Page/Pageable):** Aplicada nativamente nos endpoints de listagem, otimizando o consumo de memória do servidor e a performance das consultas.
*   **Tratamento de Exceções Global (`@ControllerAdvice`):** Captura de erros centralizada que intercepta falhas de validação, recursos não encontrados, violações de integridade do banco e erros de lógica, devolvendo sempre uma resposta limpa e padronizada através de um objeto customizado (`StandardError`).

### 🛠️ Tecnologias Utilizadas
*   **Java 21**
*   **Spring Boot 3.x**
*   **Spring Data JPA**
*   **PostgreSQL**
*   **Maven**
*   **Lombok**

---

## 🇺🇸 English

### 📝 About the Project
This is my first complete CRUD API developed using **Java** with the **Spring Boot** framework and a **PostgreSQL** database. The main goal was to go beyond the basics, applying industry standards to build a functional, secure, resilient, and high-performance REST API.

### 🚀 Features & Best Practices
*   **Full CRUD:** Creation, retrieval, update, and deletion of products and categories.
*   **Entity Relationships:** Robust association between products and their respective categories.
*   **Validated Business Rules:** Avoids duplicate records (unique names for products and categories) and validates foreign key constraints before saving data.
*   **DTO Pattern (Data Transfer Object):** Implemented to decouple database entities from the controller layer, ensuring secure data transfer.
*   **Pagination & Sorting (Page/Pageable):** Built natively into listing endpoints, optimizing server memory usage and query performance.
*   **Global Exception Handling (`@ControllerAdvice`):** Centralized error capturing that intercepts validation failures, missing resources, database integrity violations, and business logic errors, always returning a clean and standardized response through a custom object (`StandardError`).

### 🛠️ Technologies Used
*   **Java 21**
*   **Spring Boot 3.x**
*   **Spring Data JPA**
*   **PostgreSQL**
*   **Maven**
*   **Lombok**
