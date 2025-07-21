# Fundamentos de Java - Nível 1: Sua Base Sólida

Este `README` foi criado para ajudar a solidificar os conceitos essenciais do Nível 1 da pasta de java aqui, fornecendo uma base robusta para sua jornada como desenvolvedor Java.

## Sumário

1.  [Fundamentos Essenciais de Java](#1-fundamentos-essenciais-de-java)
    1.1. [Variáveis e Tipos de Dados](#11-variáveis-e-tipos-de-dados)
    1.2. [Operadores](#12-operadores)
    1.3. [Estruturas Condicionais (`if/else`, `switch`)](#13-estruturas-condicionais-ifelse-switch)
    1.4. [Estruturas de Repetição (`for`, `while`, `do-while`)](#14-estruturas-de-repetição-for-while-do-while)
    1.5. [Arrays](#15-arrays)
2.  [Programação Orientada a Objetos (POO) - Os Pilares Essenciais](#2-programação-orientada-a-objetos-poo---os-pilares-essenciais)
    2.1. [Classes e Objetos](#21-classes-e-objetos)
    2.2. [Construtores](#22-construtores)
    2.3. [Encapsulamento](#23-encapsulamento)
    2.4. [Herança](#24-herança)
    2.5. [Polimorfismo](#25-polimorfismo)
3.  [Boas Práticas Iniciais de Código](#3-boas-práticas-iniciais-de-código)
4.  [Próximos Passos para Sua Segurança](#4-próximos-passos-para-sua-segurança)

---

## 1. Fundamentos Essenciais de Java

### 1.1. Variáveis e Tipos de Dados

Em Java, é fundamental declarar o tipo de uma variável antes de usá-la. Isso informa ao compilador o tipo de dado que ela irá armazenar.

* **Tipos Primitivos:** Armazenam valores simples diretamente.
    * `int`: Números inteiros (ex: `10`, `-5`).
    * `double`: Números com casas decimais (ex: `3.14`, `-0.5`).
    * `boolean`: Valores lógicos (`true` ou `false`).
    * `char`: Um único caractere (ex: `'A'`, `'7'`).
* **Tipo `String`:** Embora seja amplamente utilizada como um tipo básico, `String` é, na verdade, uma **classe** em Java, usada para armazenar sequências de caracteres (textos).

**Exemplo:**

```java
int idade = 30; // Declara e inicializa uma variável inteira
double preco = 29.99; // Declara e inicializa uma variável double
boolean estaAtivo = true; // Declara e inicializa uma variável booleana
String nome = "Maria"; // Declara e inicializa uma String