# GraphQL Spring

This project is a simple example of how to use GraphQL with Spring Boot.

## Project Overview

This project is a simple example of how to use GraphQL with Spring Boot. It has a single endpoint that allows you to
query a list of books and authors.

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker

## Installing

1. Clone the repository:

```bash
git clone https://github.com/OmerJuve2023/GraphSpring.git
```

2. Build the project:

```bash
mvn clean install
```

## Run the project with Maven

```bash
mvn spring-boot:run
```

## Run the project with Docker

1. Build the Docker image:

```bash
docker build -t graphql-spring .
```

2. Run the Docker container:

```bash
docker run -p 8080:8080 graphql-spring
```

## Running the tests

Run the following command to run the tests:

```bash
mvn test
```
