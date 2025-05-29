# ⚙️ SETUP.md - Configuração do Ambiente Local para Projeto Athus

Este guia tem como objetivo auxiliar no processo de configuração do ambiente local de desenvolvimento para testes completos da aplicação **Projeto Athus**.

---

## ✅ Requisitos de Sistema

### ☕ Java Development Kit (JDK 21)
- Instale o **JDK 21**.
- Acesse este link para o passo a passo da instalação:  
  🔗 [Guia de Instalação do JDK](https://trello.com/c/RGtLxG7G/13-instala%C3%A7%C3%A3o-do-ambiente-de-desenvolvimento)
- Certifique-se de que a variável de ambiente `JAVA_HOME` está corretamente configurada.

---

### 🛢️ MySQL Server
- Instale o **MySQL Server**.
- Acesse o manual oficial do projeto para configuração:  
  📄 [Guia PDF de Instalação e Configuração do MySQL](https://github.com/felipemariano2511/ProjetoAthus/blob/main/Documents/Projeto%20Athus%20-%20Instala%C3%A7%C3%A3o%20MySQL.pdf)
- Crie um banco de dados com o seguinte comando:

```sql
CREATE DATABASE projeto_athus;
```

- Usuário: `root`  
- Senha: `123456`

---

### 📬 Postman
- Instale o **Postman** (https://www.postman.com/downloads/).
- Importe a **coleção de rotas da API**:
  - Arquivo `.json` disponível em:  
    📂 [`/Documents` no repositório do projeto](https://github.com/felipemariano2511/ProjetoAthus/tree/main/Documents)
- Utilize o Postman para testar endpoints como:
  - Autenticação com email/senha
  - Google OAuth2
  - Cadastro de serviços
  - Upload de imagens

---

### 🔐 Conta Google (OAuth2)
- Utilize a **conta Google do projeto**, já configurada com as credenciais de acesso OAuth2.
- Instruções e credenciais disponíveis no anexo:  
  🔗 [Conta Google do Projeto](https://trello.com/c/WepOqxht/32-conta-google-do-projeto)

---

## ⚙️ Configurações Iniciais

### 🗃️ Banco de Dados

- O Spring Boot criará automaticamente as tabelas no banco de dados ao iniciar.
- Para inserir dados de teste, edite o arquivo `application.properties`:

```properties
spring.sql.init.mode=true
```

- Após o primeiro `start` com os dados inseridos, retorne à configuração padrão:

```properties
spring.sql.init.mode=never
```

---

## ▶️ Execução da Aplicação

1. Inicie o serviço do **MySQL**.
2. Execute a aplicação Spring Boot localmente (via IDE ou `./mvnw spring-boot:run`).
3. Teste os endpoints utilizando o **Postman**:
   - Cadastro de usuários
   - Login (com JWT)
   - Redefinição de senha
   - Upload de imagens
   - Autenticação Google
   - Prestação de serviços

---

## 📫 Observações Importantes

- Os dados de exemplo são inseridos automaticamente apenas se `spring.sql.init.mode=true`.
- Emails de verificação e redefinição são enviados com **templates HTML personalizados**.
- A autenticação com Google exige conexão com a internet e uso da conta correta.

---

**Unicuritiba Projeto Athus - Todos os direitos reservados © 2025**
