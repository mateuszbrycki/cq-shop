# CQ Shop
[![Build Status](https://travis-ci.org/mateuszbrycki/cq-shop.svg?branch=master)](https://travis-ci.org/mateuszbrycki/cq-shop)
## Intro
An e-commerce system written in a microservice architecture. Communication between applications is implemented mainly with events that are processed by Kafka

The following applications are domain services:
1. [cart-service](https://github.com/mateuszbrycki/cq-shop/tree/master/cart-service)
2. [logging-service](https://github.com/mateuszbrycki/cq-shop/tree/master/logging-service)
3. [notification-service](https://github.com/mateuszbrycki/cq-shop/tree/master/notification-service)
4. [order-service](https://github.com/mateuszbrycki/cq-shop/tree/master/order-service)
5. [user-management-service](https://github.com/mateuszbrycki/cq-shop/tree/master/user-management-service)
6. [warehouse-service](https://github.com/mateuszbrycki/cq-shop/tree/master/warehouse-service)

The following applications are infrastructure/application services:
1. [api-service-gateway](https://github.com/mateuszbrycki/cq-shop/tree/master/api-gateway-service)
2. [config-server](https://github.com/mateuszbrycki/cq-shop/tree/master/config-server)
3. [eureka-server](https://github.com/mateuszbrycki/cq-shop/tree/master/eureka-server)
4. schema-registry-server
5. Kafka
6. [traffic-simulator](https://github.com/mateuszbrycki/cq-shop/tree/master/traffic-simulator)

## Technology Stack and Architecture
All the services are implemented with Java 11 and Spring Framework. Each application is built with Gradle. They use **Config Server** and **Eureka Server**. 

The **api-gateway-service** is an application that runs as a **Zuul Gateway**. It delegates all the calls into proper services. 

## Current Architecture
![Current Architecture](https://github.com/mateuszbrycki/diary/blob/master/blog/img/cq-shop-architecture.png)

### Topics and Streams
All the domain applications publish to Kafka two kinds of messages:
1. **domain events** - messages that notify about a fact that has happened in the system, e.g., a user account created.
2. **commands/queries** - messages related to CQRS implementation. Those messages are used to pass information within an application/service.

There are two main types of Kafka Topics:
1. **service-name-events** - topics for domain events. Domain services are allowed to listen to only those streams.
2. **application-command-topic** - a topic for command/queries messages

#### Messages format
All the messages are transferred as **Avro Messages**. The schema of all events is available in the [avro/](https://github.com/mateuszbrycki/cq-shop/tree/master/avro) directory. Microservices are using **schema-registry-server** to register and receive schema-related information.

# TODOs and tech debt
1. Cover services with unit tests.
2. Write integration tests. Consider [Spring Cloud Contract](https://github.com/spring-cloud-samples/spring-cloud-contract-samples).
3. Refactor traffic-simulator.
4. Do not use repositories in controllers. Push Queries and handle them in proper handlers.
5. Implement Event Sourcing for restoring applications' state.

# Publications
1. [CQ-Shop - introducing the project](https://github.com/mateuszbrycki/diary/blob/master/blog/20181010-cq-shop-introducing-the-project.md)
2. [CQ-Shop - Event Storming](https://github.com/mateuszbrycki/diary/blob/master/blog/20190214-cq-shop-event-storming.md)
3. [CQ-Shop – Events and Anomaly Detection](https://github.com/mateuszbrycki/diary/blob/master/blog/20190827-cq-shop%E2%80%93events-anomaly-detection.md)
4. [CQ-Shop – Architecture, Environment, and Tools](https://github.com/mateuszbrycki/diary/blob/master/blog/20190905-cq-shop-architecture-environment-and-tools.md)
5. [CQ-Shop – Summary](https://github.com/mateuszbrycki/diary/blob/master/blog/20190916-cq-shop-summary.md)
