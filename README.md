# ProvaApiJava

INSCRIÇÃO: 16496
RAFAEL MORAIS DA SILVA - CPF: 079.937.956-56

Projeto Maven Iniciado pelo Springr Initializr

Spring boot 3.x.x

Depedencias

Spring Web: Para criar os endpoints REST.
Spring Data JPA: Para persistência de dados.
Lombok: Para reduzir código repetitivo (getters/setters).

Criados: os models
Decidi criar duas tabelas: Artist e Album, para manter os dados conforme dados proposto. O artista está organizado por (banda ou solo) e de qual regional ele pertence.  cada artista pode ter vários albuns, cada album possui uma imagem qua vai ser armazenado no bucket do MinIO.

Digite o comando no terminal para iniciar API + MinIo + Banco de dados

docker-compose up --build

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

Faça um POST em http://localhost:8080/api/v1/artists
{
  "name": "Michel Teló",
  "genre": "Sertanejo",
  "type": "SINGER",
  "regional": {
    "id": 9
  }  
}

retorno de sucesso 200 ok

{
    "id": 8,
    "name": "Michel Teló",
    "genre": "Sertanejo",
    "type": "SINGER",
    "regional": {
        "id": 9,
        "nome": null,
        "ativo": null
    }
}
Faça um POST em http://localhost:8080/artists/1/albums
{
  "title": "Bem Sertanejo",
  "artistIds": [8]
}

retorno de sucesso 201 Created

{
    "id": 4,
    "title": "Bem Sertanejo",
    "coverImages": [],
    "artists": [
        {
            "id": 8,
            "name": "Michel Teló",
            "genre": "Sertanejo",
            "type": "SINGER",
            "regional": {
                "id": 9,
                "nome": "REGIONAL DE CUIABÁ",
                "ativo": true
            }
        }
    ]
}

Envie um Post em http://localhost:8080/api/v1/albums/1/covers

No Postman marque body -> insira um campo de "file" e nomeie como files e insira um arquivo de imagem

retorno de sucesso 200 ok
{
    "id": 1,
    "title": "10.000 Destinos (Ao Vivo)",
    "coverImages": [
        "e33ede9f-f61e-40f1-bbca-b084c3740b8b-download.png"
    ],
    "artists": [
        {
            "id": 1,
            "name": "Engenheiros do Hawaii",
            "genre": "Rock",
            "type": "BAND",
            "regional": {
                "id": 9,
                "nome": "REGIONAL DE CUIABÁ",
                "ativo": true
            }
        }
    ]
}

Acesse o Console do MinIO em http://localhost:9001 



Teste de unidade (nesse momento o teste está desligado)
mvn test
ou
./mvnw teste   

Bucket4j. Ela permite controlar o fluxo de requisições.