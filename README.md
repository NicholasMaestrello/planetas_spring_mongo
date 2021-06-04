
# Projeto de APIs do star wars

A intenção é fazer um CRUD em uma base de dados local de planetas do star wars atraves de uma API REST. Para cada planeta que for registrado na base de dados, ao realizar a obtenção do mesmo é obtido quantas vezes esse planeta ja participou de algum filme do star star wars. Para isso foi utilizado a api publica  [swapi](https://swapi.dev/).

A plataforma utilizada para o projeto foi o  [Java 11](https://jdk.java.net/) na versão 14.17 LTS.  
O banco de dados utilizado é um mongodb, e pode ser instalado atraves do site: [mongodb](https://www.mongodb.com/try/download/community).  
Alem disso tambem é necessário ter instalado o gerenciador de dependencias [maven](https://maven.apache.org/).

## Preparando o ambiente
Para preparar o abiente, baixe o fonte e então execute dentro da pasta do projeto o seguinte comando `mvn clean package`  
Com isso o código fonte vai ser compilado e a aplicação estara pronta para ser executada.  
Caso voce queira mudar URL do banco de dados modifique o arquivo de propriedades `src/main/resources/application.properties` e troque o valor da propriedade `spring.data.mongodb.host` para alterar o valor do host e a propriedade `spring.data.mongodb.database` para alterar o valor da porta do banco de dados.
Caso voce queira alterar a porta padrão do servidor altere no arquivo de propriedades `src/main/resources/application.properties` o  campo `server.port` para o valor que voce desejar.

## Executando o projeto
Para executar o projeto, basta rodar o comando`mvn spring-boot:run` com isso a aplicação vai subir um servidor express e disponibilizar a API na porta configurada (default 8080)

Para executar a API utilize os seguinte conjunto de endpoint  
| Metodo HTTP| URL | Descrição| Payload | Response |  
|--|--|--| -- | -- |  
| GET | {baseUrl}/api/planeta | Obtem a lista de planetas cadastrado na base de dados com a quantidade de filmes nos quais ele apareceu (caso tenha aparecido em algum). É possivel filtrar  pelo nome do fulme passando por `queryParam` o nome do filme, na propriedade `nome`, ex: `?nome=Tatooine`| | `[{"id": "","nome": "","clima": "","terreno": "","quantidadeAparicoes": 0}]`|  
| GET | {baseUrl}/api/planeta/:id | Obtem um unico planeta a partir do ID dele juntamente com a quantidade de filmes nos quais o mesmo apareceu. | | `{"id": "","nome": "","clima": "","terreno": "","quantidadeAparicoes": 0}` |  
| POST | {baseUrl}/api/planeta | Cadastra um novo planeta na base de dados.| `{"nome": "","clima": "","terreno": ""}` |`{"id": "","nome": "","clima": "","terreno": "","quantidadeAparicoes": 0}` |  
| PUT | {baseUrl}/api/planeta/:id | Modifica um planeta existente da base de dados a partir de um ID. | `{"nome": "","clima": "","terreno": ""}` |`{"id": "","nome": "","clima": "","terreno": "","quantidadeAparicoes": 0}` |  
| DELETE | {baseUrl}/api/planeta/:id | Exclui um planeta existente da base de dados |

## Testes
Para rodar os testes da aplicação, executar o seguinte comando `mvn test`