# **JAVA - PROJETO_SPRING**

API Rest, baseada nas classes Turma e Participante, desenvolvidos na linguagem Java, trabalhando com o Framework Spring e o Banco de dados MySQL.

## Instalação

- Java SE 15
- NPM
- Maven
- MySQL
- VSCode
- VSCode Java Extensions (https://code.visualstudio.com/docs/java/extensions)

## Uso

- Clone o repositório na sua máquina (git clone https://github.com/rafaelq80/projeto_spring);
- Abra o projeto no VSCode
- Atualize as configurações do MYSQL (Usuário, Senha e etc) no arquivo src\main\resources\application.properties
- Execute o Projeto no Spring Boot Dashboard
- Para acessar a API, utilize o endereço: http://localhost:8080
- Para acessar o Swagger, utilize o endereço: http://localhost:8080/swagger-ui-custom.html

## Rotas

### Participante

- PUT: ​/participantes​/{participanteId}
- DELETE: ​/participantes​/{participanteId}
- GET: ​/participantes
- POST: ​/participantes
- GET: ​/participantes​/nome​/{participanteNome}
- GET: ​/participantes​/id​/{participanteId}

### Turma

- PUT: ​/participantes​/{participanteId}​/turmas​/{turmaId}
- DELETE: ​/participantes​/{participanteId}​/turmas​/{turmaId}
- GET: ​/participantes​/{participanteId}​/turmas
- POST: ​/participantes​/{participanteId}​/turmas
- GET: ​/turmas​/participantes​/all
- GET: ​/turmas​/id​/{turmaId}
- GET: ​/turmas​/descricao​/{turmaDescricao}
- GET: ​/turmas​/all

## Licença

[MIT](https://choosealicense.com/licenses/mit/)
