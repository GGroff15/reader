package datastream.reader;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public interface TestContainers {

    KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("apache/kafka-native:latest"));

    PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
}
