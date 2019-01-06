# Backend Challenge

Este é projeto experimental que vista dividir um sistema monolitico em microserviços afim de melhorar a aanutenibilidade e ficar 
aderente com as novos padrões de mercado.

## Começando

Segue então o passo a passo de como rodar o projeto em seu ambiente de desenvolvimento.

### Pré-requisitos

1. Git 2.7.4 ou superior
2. Java JDK 1.8 ou superior
3. Maven 3.5.3 ou superior
4. Docker 18.06.1-ce ou superior
5. Docker Compose 1.23.2 ou superior

### Obtendo os fontes

1. Fazer o checkout do projeto em: https://github.com/samarone/backend-challenge.git
```
$ git clone https://github.com/samarone/backend-challenge.git
```
2. Entrar na pasta do projeto
```
$ cd backend-challenge
```
3. Limpar, compilar, testar e empacotar

Neste passo todos as dependências do projeto serão baixadas, então o projeto será compilado, os testes automatizados irão rodar e, em caso de sucesso, o projeto será empacotado e ficará pronto para ser usado pelo docker-compose e rodado localmente.

```
mvn clean compile test package
```

4. Rodando!
```
docker-compose up --build
```
Pronto! Depois de algum tempo os serviços estarão de pé e pronto para serem usados. 

## Experimentando a API

Junto a aplicação também fica disponível o **swagger**, que deve estar acessível através de:
```
http://localhost:8080/swagger-ui.html
```
Abra seu navegador e aponte para essa url. Através da swagger-ui será possivel conhecer todos os endpoints e também a estrutura deste, assim como testar as chamadas e seus resultados.

## Documentação da API

Usando Spring Rest Docs foi gerado um html com a documentação de utilização da api, tal arquivo se encontra na raíz do projeto com nomde de [api-guide.html](api-guide.html) , abra-o em seu ambiente de desenvolvimento usando seu navegador preferido.

## Construído com

* [Java](https://www.oracle.com/java/) - Java Platform
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework and Tools
* [Spring Cloud](http://spring.io/projects/spring-cloud) - Framework and Tools
* [Spring STS](https://spring.io/tools) - IDE
* [Linux Mint](https://linuxmint.com/) - OS

## Autor

* **Samarone Lopes** - [linkedin](https://www.linkedin.com/in/samaronelopes/)
