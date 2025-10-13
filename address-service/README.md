# Address service

This service is responsible for address management.

## Tech stack

* Build tool: Maven
* Programming language: Java
* Framework: Spring boot
* DBMS: MySQL

## Prerequisites

* Maven v3.9.11 or later
* Java SDK 25 or later
* Spring boot v3.5.6
* A MySQL server v9.4.0

### Install MySQL server by Docker

`docker run --name mysql-9.4.0 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=8082 -d mysql:9.4.0`

## Run application

`mvn spring-boot:run`

## Build application

`mvn clean package`