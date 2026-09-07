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