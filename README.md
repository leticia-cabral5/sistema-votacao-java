# Sistema de Votação em Java

## Descrição

Este projeto é um sistema de votação desenvolvido em Java. O programa permite cadastrar candidatos, realizar votos por turma, armazenar os votos em uma matriz e exibir os resultados da votação.

O sistema foi desenvolvido para praticar conceitos básicos de programação, como variáveis, arrays, matrizes, estruturas de repetição, condições, métodos e entrada de dados pelo teclado.

## Funcionalidades

* Cadastro de até 5 candidatos.
* Número dos candidatos deve ser maior que zero.
* Não permite números de candidatos repetidos.
* Não permite nomes vazios.
* Votação dividida em 3 turmas.
* Cada turma pode receber até 10 votos.
* Verificação de candidatos inexistentes.
* Armazenamento dos votos em uma matriz.
* Exibição da matriz de votos.
* Cálculo da porcentagem de votos de cada candidato.
* Resultado com duas casas decimais.
* Identificação do vencedor.
* Identificação de empate.
* Exibição do total de votos.
* Tratamento de entradas inválidas.

## Menu do sistema

O programa possui as seguintes opções:

1. Cadastrar candidatos
2. Iniciar votação
3. Exibir resultado
4. Exibir matriz de votos
5. Sair

## Estrutura do projeto

O projeto possui uma classe principal:

`SistemaVotacao.java`

Principais métodos utilizados:

* `cadastrarCandidatos()` — realiza o cadastro dos candidatos.
* `mostrarCandidatos()` — exibe os candidatos cadastrados.
* `buscarCandidato()` — verifica se um candidato existe.
* `iniciarVotacao()` — registra os votos das turmas.
* `exibirMatriz()` — mostra os votos armazenados na matriz.
* `exibirResultado()` — calcula e apresenta os resultados.
* `lerInteiro()` — recebe números do usuário e verifica entradas inválidas.

## Como executar

É necessário ter o Java instalado no computador.

No terminal, dentro da pasta do projeto, compile o programa com:

```bash
javac SistemaVotacao.java
```

Depois execute com:

```bash
java SistemaVotacao
```

## Tecnologias utilizadas

* Java
* Scanner
* Arrays
* Matrizes
* Estruturas de repetição
* Estruturas condicionais
* Métodos

## Objetivo

O objetivo do projeto é desenvolver um sistema simples de votação utilizando os conceitos fundamentais da linguagem Java e praticar a organização de um programa por meio de métodos e estruturas de dados.
