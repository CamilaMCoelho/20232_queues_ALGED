<p align="center">
  <a href="#-stack">✨ Stack</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#feito por">💻 Feito por 🚀 </a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#projeto">💻 Projeto</a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#funcionalidades">⚙️ Funcionalidades </a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#Simulação"> 🔁 Simulação </a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#Excecução">💡 Execução</a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
 

</p>

<br />

## ✨ Stack

- [ ] Java
- [ ] Maven 

## :rocket: Feito por
◼️ Camila Monteiro Coelho - RA: 82119736

◼️ Italo da Silva Alves - RA: 82118114

◼️ Julio Shoudi Fujie - RA:820116121

◼️ Luis Henrique Gil de Araujo - RA: 820112718

◼️ Manuela Santos Bernadino - RA: 820152172

◼️ Ruan Pablo Almeida Gomes - RA: 820145330

## 💻 Projeto

### *Introdução ao Projeto de Filas Brasileiras:*

O projeto "Filas Brasileiras" é uma implementação em Java que simula o funcionamento de filas de atendimento em estabelecimentos, inspirado nas peculiaridades do contexto brasileiro. Este sistema interativo processa comandos fornecidos por meio de um arquivo de entrada (filas.txt), simulando a chegada e atendimento de clientes em diferentes guichês.

### *Objetivo do Projeto:*

O objetivo principal é oferecer uma representação simplificada de um ambiente de atendimento, onde clientes podem chegar, serem adicionados a filas, desistirem do atendimento, ou serem atendidos em guichês específicos. Além disso, o sistema considera grupos de pessoas que se conhecem, influenciando a distribuição na formação das filas.
Este projeto oferece uma abordagem prática para compreender a implementação de estruturas de dados e lógica de programação em Java, aplicadas a um contexto de filas de atendimento.

### *Estruturas de Dados :*

As principais estruturas de dados utilizadas no código são:

   1.Map (filas):  
  >Tipo: Map <String, Queue<Pessoa>> <br>
  Uso: Armazena filas de pessoas, onde as chaves são strings representando os identificadores de filas, e os valores são filas implementadas como listas encadeadas (Queue). <br>

  2.Map (grupos):

  >Tipo: Map<String, Set<String>>  <br>
  Uso: Armazena grupos de pessoas que se conhecem, onde as chaves são strings representando os nomes dos grupos, e os valores são conjuntos (Set) contendo os nomes das pessoas no grupo.

  3.Lista Encadeada (LinkedList):

  >Tipo: Queue<Pessoa>  <br>
  Uso: Implementa as filas de pessoas. A estrutura de lista encadeada é eficiente para adicionar e remover elementos no início e no final da fila, que são operações comuns em filas.

  4.Conjunto (HashSet):

  >Tipo: Set<String>  <br>
  Uso: Representa conjuntos de pessoas que se conhecem. A estrutura de conjunto é útil para verificar a existência de elementos e realizar operações de interseção entre conjuntos, como verificar se duas pessoas se conhecem. <br>

Essas estruturas de dados foram escolhidas com base na eficiência das operações necessárias para as funcionalidades do projeto, como *adição*, *remoção* e *consulta de elementos*. 
O uso de listas encadeadas e conjuntos otimiza as operações específicas do contexto de filas e grupos de pessoas.

## ⚙️ Funcionalidades

1. *Criação de Filas:*
    --Comando:  `criaFila: <idFila> `
    --Cria uma nova fila com o identificador especificado.

2. *Atendimento de Filas:*
    --Comando: `atendeFila: <idFila1> <idFila2> ...`
    --Realiza o atendimento nas filas especificadas, removendo o primeiro cliente de cada fila atendida.

3. *Chegada de Clientes:*
    --Comando: `chegou: <cliente1> <cliente2> ...`
    --Adiciona os clientes às filas de acordo com a lógica de conhecimento mútuo entre pessoas.

4. *Desistência de Atendimento:*
    --Comando: `desiste: <cliente1> <cliente2> ...`
    --Remove os clientes especificados de todas as filas.

5.*Criação de Grupos de Conhecidos:*
    --Comando: `grupo: <nomeGrupo>: <pessoa1> <pessoa2>` ...
    --Define um grupo de pessoas que se conhecem mutuamente.

6. *Verificação de Conhecimento entre Pessoas:*
    --Comando: `conhece: <pessoa1> <pessoa2>`
    --Verifica se duas pessoas se conhecem e exibe o resultado.

7. *Verificação de Existência de Pessoa:*
    --Comando: `existe: <pessoa>`
    --Verifica se uma pessoa específica existe em algum grupo conhecido.

8.*Impressão do Estado Atual das Filas:*
    --Comando: `imprime`
    --Exibe o estado atual de todas as filas.

## 🔁 Simulação

### *Entrada:*
>criaFila: Guiche1
>criaFila: Guiche2
>chegou: Maria Joao
>chegou: Manuel Alface Guga
>atendeFila: Guiche1 Guiche2
>imprime
>desiste: Joao Alface
>imprime
>grupo: Amigos Maria Joao
>grupo: Familia Manuel Guga
>conhece: Maria Manuel
>conhece: Alface Guga
>imprime
>existe: Joao
>existe: Maria

### *Saída:*
>#Guiche1 [Maria, Joao]
>#Guiche2 [Manuel, Alface, Guga]

>#Guiche1 [Maria, Joao]
>#Guiche2 [Manuel]

>#Amigos [Maria, Joao] 
>#Familia [Manuel, Guga] 
>#Guiche1 [Maria, Joao]
>#Guiche2 [Manuel]

>[Maria]*conhece*[Manuel]
>[Alface]*NÃO conhece*[Guga]

>#Amigos [Maria, Joao] 
>#Familia [Manuel, Guga] 
>#Guiche1 [Maria, Joao]
>#Guiche2 [Manuel]

### *Explicação:*

1. Duas filas, Guiche1 e Guiche2, são criadas.
2. Maria e Joao chegam e são adicionados à fila Guiche1.
3. Manuel, Alface e Guga chegam e são adicionados à fila Guiche2.
4. As filas Guiche1 e Guiche2 são atendidas.
5. Joao e Alface desistem das filas.
6. Dois grupos são criados, Amigos e Familia.
7. São verificadas as relações de conhecimento entre as pessoas.
8. O estado atual das filas e dos grupos é impresso.
9. É verificado se Joao e Maria existem nos grupos.

Essa simulação cobre diversos aspectos do código, incluindo a *criação de filas*, *atendimento*, *desistência*, *criação* e *verificação de grupos*, e *verificação de existência de pessoas*.


 ## 💡 Execução

--Para executar o projeto, é necessário ter o *Java Development Kit (JDK)* instalado e configurado no sistema. <br>
--Além disso, o Visual Studio Code com a extensão *Java Extension Pack* é recomendado para facilitar o desenvolvimento.<br>
--O arquivo de *entrada filas.txt* contém os comandos que serão processados pela aplicação para simular o comportamento das filas.<br>
--Certifique-se de que o *Maven* está instalado no seu sistema. Você pode fazer o download do Maven em https://maven.apache.org/download.cgi e seguir as instruções de instalação.<br>
--Abra um terminal na pasta onde está localizado o *arquivo pom.xml* do  projeto.<br>
--Execute o seguinte comando para construir o projeto usando o Maven: *mvn clean install*<br>
--Após a construção bem-sucedida, você pode executar o projeto.Execute o seguinte comando no terminal: *java -cp target/demo.jar.ClassePrincipal* <br>
