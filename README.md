# ProvaApiJava

INSCRIÇÃO: 16496
RAFAEL MORAIS DA SILVA - CPF: 079.937.956-56

Projeto Maven Iniciado pelo Springr Initializr

Versão do Java 17
Spring boot 3.x.x

Depedencias

Spring Web: Para criar os endpoints REST.

Spring Data JPA: Para persistência de dados.

Lombok: Para reduzir código repetitivo (getters/setters).

Não conclui a implementação do filtro de domínio, e o bloqueio do refreshToken nos demais endpoints. decidi manter para o final estrategicamente a aplicação da segurança, e priorizei a agilidade na implementação das demais funcionalidades 

Criados: os models
Decidi criar duas tabelas: Artist e Album, para manter os dados conforme dados proposto. O artista está organizado por (banda ou solo) e de qual regional ele pertence.  cada artista pode ter vários albuns, cada album possui uma imagem qua vai ser armazenado no bucket do MinIO.

Antes de iniciar a aplicação, precisamos informar o IP(do servidor que hospeda a API) do requisitante para gerar o link pré-assinado, atualize com seu endereço IP a variavel minio.external-url=http://x.x.x.x:9000 no arquivo java\com\example\resources\application.properties

Digite o comando no terminal para iniciar API + MinIo + Banco de dados

docker-compose up --build

Após iniciado acesse o link http://localhost:8080/swagger-ui/index.html

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

no postman para todas as requisições insira o token no header -> marque Authorization e insira a palavra "Bearer +token aqui" 

Faça um POST em http://localhost:8080/api/v1/artists
{
  "name": "Michel Teló",
  "genre": "Sertanejo",
  "type": "SOLO",
  "regional": {
    "id": 9
  }  
}

retorno de sucesso 200 ok

{
    "id": 8,
    "name": "Michel Teló",
    "genre": "Sertanejo",
    "type": "SOLO",
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
            "type": "SOLO",
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
            "type": "BANDA",
            "regional": {
                "id": 9,
                "nome": "REGIONAL DE CUIABÁ",
                "ativo": true
            }
        }
    ]
}

Envie um get para http://localhost:8080/api/v1/

{
    "content": [
        {
            "id": 1,
            "title": "10.000 Destinos (Ao Vivo)",
            "artistNames": [
                "Engenheiros do Hawaii"
            ],
            "coverUrls": []
        },
        {
            "id": 3,
            "title": "Imunidade Musical",
            "artistNames": [
                "Charlie Brown Jr."
            ],
            "coverUrls": [
                "http://172.16.51.97:9000/album-covers/2bfc934a-968d-4e5c-926a-3a42dcc0c714-download.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260204%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260204T132453Z&X-Amz-Expires=1800&X-Amz-SignedHeaders=host&X-Amz-Signature=23cfc56e726925fb1b9722fc00ec6da3bbcaa495286046c971c73d1780000f87"
            ]
        },
        {
            "id": 2,
            "title": "Admirável Chip Novo",
            "artistNames": [
                "Pitty"
            ],
            "coverUrls": [
                "http://172.16.51.97:9000/album-covers/78364b87-7db4-4bc3-9346-d02c08c28d6d-download.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260204%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260204T132453Z&X-Amz-Expires=1800&X-Amz-SignedHeaders=host&X-Amz-Signature=89903279ecfc0cb5b94fe15a4d5b1aed83a485c9efabc2928f3c261070109b83"
            ]
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 3,
    "first": true,
    "numberOfElements": 3,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "empty": false
}

Para atualizar um artista, envie um PUT http://localhost:8080/api/v1/artists/8
em raw selecione JSON
{
  "name": "Michel Teló - Atualizado",
  "genre": "Sertanejo Universitário",
  "type": "Solo",
  "regionalId": 2
}

Para atualizar um album, envie um PUT http://localhost:8080/api/v1/albums/1
em raw selecione JSON

{
  "title": "teste edicao",
  "artistIds": [1] 
}

Aperte Ctrl + click no link do coverUrls e imagem irá abrir no navegador se não tiver passado 30 minutos que o link foi gerado

Acesse o Console do MinIO em http://localhost:9001 



Teste de unidade
mvn test
ou
./mvnw teste   

Bucket4j. Ela permite controlar o fluxo de requisições.

No postman click em new, e selecione websocket, e insira ws://localhost:8080/ws-music e click em send e perceba a mensagem Connected to ws://localhost:8080/ws-music

Dê um duplo click no arquivo teste.html, vai abrir no navegador e uma mensagem de "Conectado ao WebSocket!" deve aparecer.
Faça uma alteração no Album e veja a mensagem recebida.
