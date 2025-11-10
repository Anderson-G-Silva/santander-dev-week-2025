## Santander Dev Week 2025
Java RESTful API criada para a Santander Dev Week

## Diagrama de classes

```mermaid
classDiagram
    class Client {
        +String name
        +String CPF
        +Account account
        +Card card
        +Feature[] features
        +News[] news
    }

    class Address {
        +String cep
        +String street
        +String complement
        +String unit
        +String district
        +String city
        +String stateCode
        +String state
        +String region
        +String ibge
        +String gia
        +String areaCode
        +String siafi
    }

    class Account {
        +String Number
        +String Agency
        +BigDecimal Balance
        +BigDecimal Limit
    }

    class Card {
        +String number
        +BigDecimal limit
    }

    class Feature {
        +String icon
        +String description
    }

    class News {
        +String icon
        +String description
    }

    Client "N" *-- "1" Address
    Client "1" *-- "1" Account
    Client "1" *-- "1" Card
    Client "1" *-- "1..N" Feature
    Client "1" *-- "1..N" News
```

## Fluxogramas
### Cadastro de clientes

```mermaid
flowchart TD
    A([Start]) --> B[Input customer data]
    B --> C{Is customer registered?}
    C -->|Yes| I([End])
    C -->|No| D{Validate CPF}
    D -->|Invalid| A
    D -->|Valid| E[Search ZIP code and register address]
    E --> F[Generate checking account number]
    F --> G[Generate credit card number]
    G --> H[Save customer data]
    H --> I([End])
```
### Consulta por ID, CPF e Nome completo
```mermaid
flowchart TD
    A([Start]) --> B[Enter ID, CPF, or Full Name]
    B --> C{Does the registration exist?}
    C -->|No| A
    C -->|Yes| D[Display customer data]
    D --> E([End])
```
### Consulta todos
```mermaid
flowchart TD
    A([Start]) --> B[Run data query]
    B --> C[Display customer data]
    C --> D([End])
```

### Excluir cliente por ID ou CPF

```mermaid
flowchart TD
    A([Start]) --> B[Enter ID or CPF]
    B --> C{Does the registration exist?}
    C -->|No| A
    C -->|Yes| D[Delete customer]
    D --> E([End])
```
### Atualizar dados por ID ou CPF

```mermaid
flowchart TD
    A([Start]) --> B[Enter ID or CPF]
    B --> C{Does the registration exist?}
    C -->|No| A
    C -->|Yes| D[Input customer data to be updated]
    D --> E{Is the entered ID the same as in the record?}
    E -->|No| A
    E -->|Yes| F[Save customer data]
    F --> G([End])
```
## Linguagens utilizadas
- Java Versão 21
- Spring Boot 3.5.6
- Railway
- 

## Referências

- [DIO - Trilha Java básico](https://github.com/digitalinnovationone/santander-dev-week-2023-api)


## Autores

- [@Anderson-G-Silva](https://github.com/Anderson-G-Silva)

