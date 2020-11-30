![Desafio Tatic] (https://bytebucket.org/tatic_rhdev/desafioengenheiro/raw/2458d4a6657c0166bbba4ca069f66830eee5e17c/logo-tatic.png =150x76 "Desafio Tatic Estágio")

## Desafio Tatic

Em empresas de telecomunicações, os registros de chamadas, uso de dados e mensagens são armazenados na forma de um tipo de registro chamado CDR. O CDR contêm todas as informações de um determinado evento e são gerados milhões de registros todos os dias.
Os arquivos de CDR's são enviados periodicamente pela mediação e armazenados em um diretório específico.

Desenvolva um sistema que receba arquivos de CDR's periodicamente e armazene-os da forma mais otimizada possível, de forma a otimizar custo com infraestrutura e oferecer um bom desempenho.
O sistema deve oferecer um front-end web no qual seja possível realizar buscas de CDR's por periodo e por indexadores.

As diversas áreas dessas empresas precisam buscar os dados de maneira eficiente. Algumas das buscas são:

* Busca de todas as chamadas de um originador em um determinado períoodo
* Busca de todas as chamadas recebidas por um determinado número em um determinado período
* Busca de todas as chamadas que passaram por uma determinada antena

No arquivo files.tar.gz, você encontrará uma lista de arquivos com registros de chamadas, conforme especificações a seguir.

* A coluna que representa a antena é CELL_IN para antena de entrada (chamadas entrantes) e CELL_OUT para antena de saída (chamadas saintes).
* O número originador da chamada está na coluna CALLING_NUMBER e o número destino está na coluna CALLED_NUMBER.
* A coluna CALL_TYPE representa o tipo de chamada, 1 chamada sainte e 2 chamada entrante.
* A coluna IMSI representa um cliente unicamente.
* A coluna SERIAL_NUMBER representa o código do aparelho que realizou a chamada.
* A coluna CALL_DURATION é a duração da chamada em segundos.
* A coluna START_TIME é o timestamp do inicio da chamada.
* As outras colunas serão usadas apenas para recuperação e seu significado não é necessário.

```
    EXCHANGE_ID;CALLING_NUMBER;CALLED_NUMBER;SERIAL_NUMBER;CALL_TYPE;CALL_DURATION;START_TIME;IMSI;SWITCH;CELL_IN;CELL_OUT;TECNOLOGIA;FILE_NAME;FIRST_LAC;LAST_LAC;GGSN_ADDRESS
    8138;5521987366501;5521908100740;5604248321560188;1;98;2019-10-05T05:56:17;8136378640573727;39;65234;00056;A;calls.txt;f86a89;bf1043;endpoint.ggsn.tatic.com
    2082;5521900070358;5511970605214;5641340773308212;2;64;2019-10-01T07:51:32;3130005345877044;45;00072;65234;B;calls.txt;d6b5ba;2e4871;endpoint.ggsn.tatic.com
    2289;5521946272014;5521908476414;6555528327464448;1;318;2019-10-23T13:23:10;6673275331277723;80;65234;65234;A;calls.txt;691b6c;66e885;endpoint.ggsn.tatic.com
    4418;5521943062830;5511947307567;3524862623171027;2;270;2019-10-19T16:12:46;8721648346805728;22;65234;00084;B;calls.txt;92da89;b2b700;endpoint.ggsn.tatic.com
    8099;5511934503573;5531963457733;5307621106101687;1;334;2019-10-28T09:16:23;4036125477586754;22;65234;65234;A;calls.txt;ca0ad2;9c592d;endpoint.ggsn.tatic.com
    9655;5511936554323;5511928735222;0323314573232566;2;288;2019-10-11T15:56:24;0314260520317028;72;65234;00205;B;calls.txt;3f9c14;a0e75e;endpoint.ggsn.tatic.com
    6276;5521937485782;5511911857043;4015541614151871;1;223;2019-10-29T04:38:19;4670717343134843;80;60542;00056;A;calls.txt;c66229;5661af;endpoint.ggsn.tatic.com
    7956;5521947606588;5521964036206;8554087705132788;2;184;2019-10-09T05:43:14;7612136753566556;45;00072;00056;B;calls.txt;89626b;81fd37;endpoint.ggsn.tatic.com
    9372;5531956202750;5511966801063;7257362521470148;1;261;2019-10-20T00:41:27;3127846123313470;72;00084;60542;A;calls.txt;4ff8b7;15e13c;endpoint.ggsn.tatic.com
    9917;5531905325437;5531934154518;7015405572621345;2;208;2019-10-12T09:20:41;4610640064438776;72;00084;00056;B;calls.txt;da5f76;002371;endpoint.ggsn.tatic.com
    7347;5521978073827;5531984760752;1083704670055286;1;170;2019-10-03T19:18:44;5228135712634222;80;00205;00072;A;calls.txt;cd2daa;f03c48;endpoint.ggsn.tatic.com
    3146;5511984308411;5521901386104;8786088428818121;2;208;2019-10-08T07:11:02;1546862072458717;80;65234;60542;B;calls.txt;187d00;a1df40;endpoint.ggsn.tatic.com
```

Cada linha desse arquivo é um CDR contendo dados da chamada.

## Requisitos

* Os arquivos devem ser armazenados em um formato estruturado específico, como por exemplo, AVRO, ORC ou PARQUET. Caso julgue necessário, você poderá usar outro formato, ainda que próprio para armazenar os dados. Justifique sua escolha.
* Você deve particionar e indexar os dados para que as buscas sejam rápidas.
* Você deve construir o front-end web usando uma das tecnologias indicadas: React, Vue ou Angular.
* O backend que armazena os dados deve ser construído em uma das linguanges de sua preferência entre as opções (Node.js, Python, Go ou Java) e deve executar em modo daemon.
* Você deverá construir uma suite de testes automatizados.
* Você deve provisionar a infraestrutura da sua aplicação usando IaC (de preferência Terraform ou Ansible).
* Qualquer tecnologia além das indicadas poderá ser utilizada para complementar a solução.

## Protótipo Front-end

Utilize o protótipo a seguir como inspiração para sua interface.

![Prototipo](Prototipo-pt.png "Protótipo")

## Recomendações

As recomendações abaixo não são obrigatórias, mas serão apreciadas se forem resolvidas.

* Armazenar grandes volumes de dados
* Otimizar espaço gasto em disco
* Armazenar grandes arquivos que não cabem na memória principal
* Otimizar o uso de memória RAM
* Escolher a forma de particionar os dados para facilitar as buscas
* Permitir buscas por vários campos, evitando percorrer todos os arquivos

---

## Avaliação

Os seguintes critérios serão avaliados

+ Uso eficiente do disco: o programa de armazenamento precisa se preocupar em usar o menor espaço em disco possível para os dados processados.
+ Busca eficiente dos dados: o programa de busca precisa se preocupar em trazer os resultados solicitados no menor tempo possível.
+ Qualidade do código (uso de boas práticas e adoção de um bom design).
+ Robustez das aplicações de armazenamento e pesquisa.
+ Existência de testes automatizados.
+ Aderência aos serviços de cloud

## Entrega

* Criar um repositório na sua conta particular do github ou bitbucket com acesso público
* O projeto deve conter um arquivo Readme.md com as instruções para compilação e execução
* Adicionar seções justificando suas escolhas
* O sistema deve ser executado com docker e docker-compose