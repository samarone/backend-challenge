Ao implementar o desafio eu analisei o proposto e cheguei a conclusão que deveria começar criando a estrutura necessária para
ter microserviços usando a plataforma Spring Cloud, assim:

1 - Movi o projeto original para dentro do módulo store-service.
2 - Fiz as configurações necessárias para trabalhar com o Spring Config e Eureka.
3 - Criei os artefatos necessários para trabalhar com docker e docker-compose.
4 - Implementei o serviço Rest do Store e toda a infra necessária para tal: Spring MVC, Spring Data, Database H2 
    e Liquidbase para gerir o bootstrap do banco de dados, assim como abri a possibilidade para manter as modificações com migrations.
5 - Construí os testes unitários e documentação da API usando Spring Docs Rest através dos próprios testes.
6 - Criei a documentação para orientar de como rodar a aplicação localmente usando docker-compose.

Dificuldades:

Enfrentei dois problemas ligados a infraestrutura:

1 - Esee me tomou muito tempo e consiste em fazer com que os serviços eureka-service e demais pudessem esperar a subida do config-service.
    O config-service ao ser startado pelo docker-compose sinaliazava sua subida - que é uma característica do docker-compose - antes do serviço 
    estar realmente pronto para receber requisições. Depois de muito tempo acabei implementando um workaround de health check que pode ser visto 
    no docker-compose.yml (ver healthcheck) na raíz do projeto.

2 - Os serviços quando rodando sobre o docker-compose teimavam em não se comunicarem pela rede interna gerada. Acredito que seja algum problema local
    da instalação do meu docker, consegui resolver também com um workaround que pode ser visto no docker-compose.yml, ver networks.

Simplificações:

1 - O config-service foi configurado para funcionar com profile native, ou seja, as configurações devem ser postas no classpath da aplicação para poderem ser
    reconhecidas pelo serviço, e para este caso seria necessário acessar a instância docker e lá colocar os arquivos, apesar de poder usar volumes do docker para 
    manter os arquivos externos a instância, ainda sim só deve ser usado para propósito de desenvolvimento. Existe outras duas formas muito mais robustas
    para guardar as configurações, que são: um repositório git (padrão) e também conectando ao Spring Cloud Vault que seria a opção mais segura e robusta.

2 - Estou usando um banco H2 rodando em memória para simplificar o setup e bootstrap da aplicação, porém seria facilmente configurado para usar um banco de dados
    mais tradicional como PostgreSQL, para tal seria necessário definir as configurações do datasource fixamente ou um profile diferente onde pudesse configurar externamente as váriaveis necessárias. Poderíamos adicionar instâncias de banco de dados usando o docker-compose.xml facilmente.

3 - O Swagger está ativo por padrão, porém para produção seria impreterível que fosse desligado. Esse On-Off também pode ser implementado usando profiles do spring.


Obrigado pelo desafio, sempre bom tentar fazer algo de qualidade alta em tempo reduzido.


