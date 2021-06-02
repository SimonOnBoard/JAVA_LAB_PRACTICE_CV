package com.itis.practice.team123.cvproject;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.lifecycle.Startables;

import java.io.File;

@Slf4j
public class TestCheckConfiguration {
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("bd")
            .withUsername("postgres")
            .withPassword("postgres")
            .withLogConsumer(new Slf4jLogConsumer(log))
            .waitingFor(Wait
                    .forLogMessage(".*database system is ready to accept connections.*", 1));

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            container.start();
//            environment.start();

            TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword(),
                    "spring.datasource.driver-class-name=" + container.getDriverClassName()
            ).applyTo(configurableApplicationContext.getEnvironment());
            log.info(container.getLivenessCheckPortNumbers().toString());

        }


//        @ClassRule
//        public static DockerComposeContainer environment =
//                new DockerComposeContainer(new File("src/test/resources/compose.yml"))
//                        .withExposedService("postgres_1", 5555)
//                        .withExposedService("pgadmin_1", 4444);
    }
}
