# ProvaApiJava

INCRIÇÃO: 16496
RAFAEL MORAIS DA SILVA

Projeto Maven Iniciado pelo Springr Initializr

Spring boot 3.x.x

Depedencias

Spring Web: Para criar os endpoints REST.
Spring Data JPA: Para persistência de dados.
H2 Database: Banco de dados em memória (ideal para testes rápidos).
Lombok: Para reduzir código repetitivo (getters/setters).

Criados: os models
Artista e Album

Acesse o console do H2: Vá em http://localhost:8080/h2-console para ver as tabelas

Para rodar a aplicação no VS code click 'Run Java': http://localhost:8080/artist

Faça um POST para http://localhost:8080/auth/login enviando um JSON de login. Você receberá um accessToken.
exemplo:
{
    user: 'seunome'
}

Após receberá dois tokens como no exemplo abaixo
{
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NjkxOTQ4MDAsImV4cCI6MTc2OTI4MTIwMH0.G_F7-broRS8Rg-carlqF4tVm29G4k32LQUHtTeUIMek",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NjkxOTQ4MDAsImV4cCI6MTc2OTE5NTEwMH0.5POLC0TZjPohUDnpbt4nQ9BmtP28dPFtrN6VR3VURs4"
}

Insira um exemplo

Faça um POST em http://localhost:8080/artists
{
    "name": "Pink Floyd",
    "genre": "Progressive Rock"
}
Faça outro POST em http://localhost:8080/artists/1/albums
{
    "title": "The Dark Side of the Moon",
    "releaseYear": 1973
}

Digite o comando no terminal

docker-compose up -d

Acesse o Console do MinIO em http://localhost:9001 

Execute o comando para gerar o bucket padrão
./mvnw spring-boot:run 

Teste de unidade
mvn test
ou
./mvnw teste   

Bucket4j. Ela permite controlar o fluxo de requisições.