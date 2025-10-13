# Notification service

This microservice is responsible for send email notifications to users.

## Tech stack

* Build tool: Maven
* Programming language: Java
* Framework: Spring boot
* DBMS: MongoDB
* Message queue: Kafka
* Third-party: Brevo (Email service)

## Prerequisites

* Maven v3.9.11 or later
* Java SDK 25 or later
* Spring boot v3.5.6
* A MongoDB server v8.0.15
* A Kafka server v4.1.0
* Brevo API key

### Install MongoDB server by Docker

`docker run --name mongodb-8.0.15 -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=8082 -d mongo:8.0.15`

### Install Kafka

`docker-compose up -d`

### Get Brevo API key

- Go to [Developer page of Brevo](https://developers.brevo.com/)
- Click **My API key**. If not have, click [here](https://app.brevo.com/) go to the **Brevo** home page.
- Go to _Settings_ >> Find _an API settings_ >> Click _Generate a new API Key_.
- Finally, copy **API key** and paste to your project.

## Run application

`mvn spring-boot:run`

## Build application

`mvn clean package`