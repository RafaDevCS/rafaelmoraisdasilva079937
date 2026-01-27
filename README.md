# ProvaApiJava

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

docker-compose up --build -d
Baixará o Maven e o Java.


1 Docker: docker-compose up -d (sobe Postgres e MinIO).

2 Bucket: Acesse localhost:9001 e crie o bucket album-covers (ou deixe o script de Auto-create que fizemos rodar).

3 Build: No terminal da pasta do projeto: mvn clean install.

4 Run: mvn spring-boot:run.

5 Swagger: Acesse http://localhost:8080/swagger-ui/index.html para ver se Sandy & Junior já aparecem lá (via Flyway).

Limpe o cache do Maven:

1
./mvnw clean install
Suba a infraestrutura:

2
docker compose up -d
Rode a aplicação:

3
./mvnw spring-boot:run