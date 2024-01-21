package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractionBaseTest {

    static final MongoDBContainer MONGO_DB_CONTAINER;
    static final RabbitMQContainer RABBIT_MQ_CONTAINER;

    static{
        MONGO_DB_CONTAINER =  new MongoDBContainer(
                DockerImageName.parse("mongo:4.0.3"));

        MONGO_DB_CONTAINER.start();

        RABBIT_MQ_CONTAINER = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.7.25-management-alpine"));
        RABBIT_MQ_CONTAINER.start();

    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {

    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", RABBIT_MQ_CONTAINER::getHost);
        registry.add("spring.rabbitmq.port", RABBIT_MQ_CONTAINER::getAmqpPort);
        registry.add("spring.rabbitmq.username", RABBIT_MQ_CONTAINER::getAdminUsername);
        registry.add("spring.rabbitmq.password", RABBIT_MQ_CONTAINER::getAdminPassword);
        registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    }

}
