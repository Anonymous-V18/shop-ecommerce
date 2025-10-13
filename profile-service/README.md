# Profile Service

This service is responsible for profile management.

## Tech stack

* Build tool: Maven
* Programming language: Java
* Framework: Spring boot
* DBMS: Neo4J

## Prerequisites

* Maven v3.9.11 or later
* Java SDK 25 or later
* Spring boot v3.5.6
* A Neo4J server v5

### Install Neo4J server by Docker

`docker run --name neo4j-5 -p 7474:7474 -p 7687:7687 -e 'NEO4J_AUTH=neo4j/Neo4j@8082' -d neo4j:5`

## Run application

`mvn spring-boot:run`

## Build application

`mvn clean package`