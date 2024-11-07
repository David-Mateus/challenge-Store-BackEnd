# StoreApi
### Tecnologias Utilizadas
- Java 17
- Spring Boot (Framework principal)
- Spring Data JPA (para comunicação com o banco de dados)
- MySQL (banco de dados)
- Docker (para rodar o banco de dados)
- Swagger (documentação da API)
- HATEOAS (Hypermedia As The Engine Of Application State)
- CORS (para permitir requisições de diferentes origens)
- Spring Security e JWT (implementação comentada devido a erro, mas presente no código)
### Funcionalidades
- CRUD para relações OneToMany: Exemplo: uma categoria pode ter vários produtos.
- Swagger UI: A documentação da API está disponível em http://localhost:8080/swagger-ui/index.html#/.
- CORS: A configuração de CORS foi implementada para permitir requisições de origens diferentes.
- HATEOAS: Implementação de links de navegação para facilitar a integração com a API.
