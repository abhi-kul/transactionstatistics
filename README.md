# N26 Backend Coding Challenge

## Technology Stack :
  - Java8
  - Junit
  - Maven
  - IDE Used : Intellij

## Architecture / Design :

  - The main requirement was to execute /GET endpoint in O(1) time. To achieve same we have used
  AtomicReferenceArray which is thread safe too. Every time when we are inserting a new transaction
  in storage , we are calculating all required parameters and putting it into storage so that whenever
  GET call will come , we will have all available details.

  - To handle expiry of transaction entity in storage , we have created annotation named "WithinAMinute".

## Endpoint details :

    1. Request :--

    POST /transactions

    {
        "amount": 12.3,
        "timestamp": 1478192204000
    }
    Response :--
    HTTP/1.1 201 Created
    2. Request :--
    GET /statistics
    Response :--
    HTTP/1.1 200 OK
    Content-Type: application/json

    {
        "sum": 12,
        "avg": 6,
        "max": 6,
        "min": 6,
        "count": 2
    }

## How to Run :
  - To verify test and to build :

    ```
    mvn clean verify
    ```

  - To run application

    ```
        mvn clean verify

    ```

    ##OR

    ```
        mvn spring-boot:run







