# Projeto Spring Security DIO

Este projeto é uma aplicação backend desenvolvida em **Spring Boot** com foco em **autenticação e autorização** usando **Spring Security**. Ele foi construído como parte de um desafio ou curso da Digital Innovation One (DIO) e utiliza autenticação baseada em banco de dados com `UserDetailsService`.

## ✨ Funcionalidades

- Autenticação de usuários via banco de dados
- Autorização baseada em papéis (roles): `USER`, `ADMIN`
- Controle de acesso por rotas com `@PreAuthorize`
- Criação automática de usuários padrão (`admin`, `user`)
- Login via HTTP Basic Auth
- Spring Data JPA com banco de dados relacional

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- Maven
- Lombok

---

## 📁 Estrutura de Pastas

```bash
com.champs.spring_security
├── config # Configurações de segurança
├── controller # Endpoints REST
├── init # Inicialização de dados
├── model # Entidade JPA (AppUser)
├── repository # Repositório JPA
├── service # Implementação de UserDetailsService
└── SpringSecurityApplication.java
```


---

## ▶️ Como Executar o Projeto

### 1. Pré-requisitos

- Java 17+
- Maven

### 2. Clone o repositório

```bash
git clone https://github.com/champs-8/projeto-springsecurity-dio.git
cd projeto-springsecurity-dio
```

### 3. Execute a aplicação

```
./mvnw spring-boot:run
```
> A aplicação será iniciada em: http://localhost:8080

---

### 🔐 Usuários de Teste

| Usuário | Senha    | Papel |
| ------- | -------- | ----- |
| admin   | admin123 | ADMIN |
| user    | user123  | USER  |

---

### 🔀 Endpoints REST

| Método | Rota        | Acesso      | Descrição                       |
| ------ | ----------- | ----------- | ------------------------------- |
| GET    | `/`         | Público     | Mensagem de boas-vindas         |
| GET    | `/users`    | USER, ADMIN | Apenas usuários autenticados    |
| GET    | `/managers` | ADMIN       | Apenas usuários com papel ADMIN |

---

### 🔧 Segurança
- Autenticação com Spring Security + UserDetailsService

- Roles são armazenadas em tabela separada (tab_user_roles)

- Acesso controlado por anotações @PreAuthorize e HttpSecurity

- Login via httpBasic() (sem formulário customizado)

---

### 💡 Observações
Para simplificação, o projeto usa NoOpPasswordEncoder, o que não é seguro para produção. Para ambientes reais, utilize BCryptPasswordEncoder (já disponível no projeto).

Banco de dados pode ser facilmente trocado de H2 para outro como PostgreSQL ou MySQL alterando as configurações.

---

## 📄 Licença
Este projeto é apenas para fins educacionais e segue os termos de uso da Digital Innovation One (DIO).

## 🙋‍♂️ Autor
Desenvolvido por @champs-8



