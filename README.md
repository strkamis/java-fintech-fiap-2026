# Java Fintech FIAP 2026

Projeto didático de POO com contas, clientes e histórico de transações.

## Executar

Requer Java 17 e Maven:

```bash
mvn test
mvn exec:java -Dexec.mainClass=br.com.fiap.fintech.Main
```

O exemplo demonstra transferência entre uma `ContaCorrente` e uma
`ContaPoupanca`, uso de limite, rendimento e polimorfismo por referência
`Conta`.
Projeto demonstrativo de orientação a objetos em Java, com classes encapsuladas
de cliente e contas, herança entre `Conta` e suas modalidades e polimorfismo no
cálculo de rendimento e saque.

Para compilar e executar a demonstração:

```bash
mvn test
mvn exec:java
```
