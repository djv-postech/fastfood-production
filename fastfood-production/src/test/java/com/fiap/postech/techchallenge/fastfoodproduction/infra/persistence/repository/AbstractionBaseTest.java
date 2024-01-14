package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractionBaseTest {

    static final MongoDBContainer MONGO_DB_CONTAINER;

    static{
        MONGO_DB_CONTAINER =  new MongoDBContainer(
                DockerImageName.parse("mongo:4.0.3"));

        MONGO_DB_CONTAINER.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    }

}
