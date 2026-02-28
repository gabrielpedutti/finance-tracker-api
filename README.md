<div align="center">

# 💹 Finance Tracker API

---

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring](https://img.shields.io/badge/Spring_Boot-3.4-green?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Postgres](https://img.shields.io/badge/PostgreSQL-4479A1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)

</div>

API REST desenvolvida para gestão financeira pessoal, permitindo o controle detalhado de receitas (fixas e variáveis, como extras e trabalhos autônomos) e despesas categorizadas.

## 🎯 Diferenciais Técnicos
O projeto foi construído aplicando padrões de arquitetura de mercado para garantir escalabilidade, segurança e facilidade de manutenção:

* **Arquitetura em Camadas**: Separação de responsabilidades entre Controllers (HTTP), Services (Negócio) e Repositories (Persistência).
* **Global Error Handling**: Implementação de `@RestControllerAdvice` para padronizar respostas de erro (400, 404, 500) com mensagens amigáveis e seguras.
* **Imutabilidade com Records**: Uso de Java Records para DTOs, garantindo a integridade dos dados trafegados.
* **Database Versioning**: Utilização do Flyway para versionamento do esquema do banco de dados.
* **Bean Validation**: Regras de validação aplicadas diretamente nos DTOs para garantir a qualidade dos dados de entrada.

## 🛠️ Tecnologias Utilizadas
* **Java 21** (LTS)
* **Spring Boot 3.4**
* **Spring Data JPA**
* **PostgreSQL**
* **Flyway Migration**
* **Lombok**
* **Jakarta Validation**

## 🚀 Como Rodar o Projeto

1. Certifique-se de ter o **Java 21** e o **PostgreSQL** instalados.
2. Clone o repositório: `git clone https://github.com/gabrielpedutti/finance-tracker-api.git`
3. Configure as credenciais do seu banco no arquivo `src/main/resources/application.properties`.
4. Execute o comando: `./mvnw spring-boot:run`

## 🛣️ Endpoints da API

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/transactions` | Lista todas as transações cadastradas. |
| `POST` | `/transactions` | Registra uma nova receita ou despesa. |
| `GET` | `/transactions/{id}` | Busca detalhes de uma transação específica. |
| `PUT` | `/transactions` | Atualiza os dados de uma transação existente. |
| `DELETE` | `/transactions/{id}` | Remove uma transação do sistema. |

## 🧩 Exemplo de Resposta de Erro Padronizada
Em caso de erro de validação (ex: valor negativo ou campo ausente), a API responde com:

```json
{
  "timestamp": "2026-02-27T20:30:00",
  "status": 400,
  "error": "Requisição inválida",
  "message": "Campo: amount - deve ser um valor positivo"
}
```

## 🏗️ Roadmap de Evolução

- [x] **Tratamento de Erros Global**: Implementação de `@RestControllerAdvice` e DTOs de erro padronizados.
- [x] **Arquitetura em Camadas**: Refatoração do Controller para a camada de Service.
- [x] **Validação de Dados**: Uso de Bean Validation para integridade dos campos.
- [ ] **Spring Security + JWT**: Implementação de autenticação e autorização para proteção de dados sensíveis.
- [ ] **Dashboard de Finanças**: Endpoints para resumo mensal (Saldo, Receitas vs Despesas) e filtros por categoria.
- [ ] **Testes Automatizados**: Cobertura de testes unitários e de integração com JUnit 5 e Mockito.
- [ ] **Documentação OpenAPI**: Interface interativa com Swagger UI para exploração e testes da API.

---
<p align="center" style="margin-top: 20px; font-size: 14px; color: #888;">
Desenvolvido com 🍏💚 por <a href="https://github.com/gabrielpedutti">Gabriel Pedutti</a>
</p>