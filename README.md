# 📚 LibraryLoop - Backend API

LibraryLoop é uma API REST desenvolvida com Spring Boot que permite:

-   🔍 Buscar livros na OpenLibrary
-   📖 Verificar disponibilidade de leitura online
-   👤 Criar e gerenciar usuários
-   📚 Criar e gerenciar listas de leitura
-   ➕ Adicionar livros à lista do usuário

> ⚠️ Atualmente o projeto é apenas Backend. O frontend será desenvolvido
> futuramente utilizando React.

------------------------------------------------------------------------

# 🏗️ Tecnologias Utilizadas

-   Java 17+
-   Spring Boot
-   Spring Web
-   Spring Data JPA
-   Lombok
-   RestTemplate
-   OpenLibrary API

------------------------------------------------------------------------

# 🚀 Como Rodar o Projeto

``` bash
git clone <repo>
cd LibraryLoop
./mvnw spring-boot:run
```

Aplicação roda por padrão em:

    http://localhost:8080

------------------------------------------------------------------------

# 🧠 Arquitetura Atual

    Controller
       ↓
    Service
       ↓
    OpenLibraryClient
       ↓
    OpenLibrary API

------------------------------------------------------------------------

# 🔜 Próximos Passos

-   Adicionar autenticação (JWT)
-   Implementar paginação real
-   Adicionar cache
-   Desenvolver frontend com React
-   Deploy em nuvem

------------------------------------------------------------------------

# 🎯 Objetivo

Criar uma API robusta para gerenciamento de leitura, permitindo
organização pessoal, busca eficiente e integração com leitura online.
