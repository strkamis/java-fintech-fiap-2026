# java-fintech-fiap-2026

Projeto Java que exemplifica os conceitos de Programação Orientada a Objetos
(encapsulamento, herança e polimorfismo) aplicados a um domínio de Fintech.

## Estrutura

- `com.fintech.model.Pessoa` — superclasse abstrata com dados comuns (nome, CPF).
- `com.fintech.model.Cliente` — subclasse de `Pessoa`, representa um cliente e suas contas.
- `com.fintech.model.Conta` — superclasse abstrata de contas bancárias (depósito, saque, transferência, rendimento).
- `com.fintech.model.ContaCorrente` — subclasse de `Conta` com limite de cheque especial e tarifa mensal.
- `com.fintech.model.ContaPoupanca` — subclasse de `Conta` com rendimento mensal sobre o saldo.
- `com.fintech.model.CartaoCredito` — classe independente que representa um cartão de crédito vinculado a um cliente.
- `com.fintech.Main` — classe de execução (teste) que instancia os objetos e invoca seus métodos.

## Como executar

Usando Maven:

```bash
mvn compile exec:java -Dexec.mainClass=com.fintech.Main
```

Ou compilando manualmente com `javac`/`java`:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.fintech.Main
```
