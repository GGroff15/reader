package datastream.reader.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableConfigurationProperties(KafkaTopicsProperties.class)
class KafkaTopicsPropertiesIT {

    @Autowired
    private KafkaTopicsProperties kafkaTopicsProperties;

    @Test
    @DisplayName("Should load Kafka topics definition")
    void shouldLoadKafkaTopicsDefinition() {
        Map<String, KafkaTopicsProperties.Definition> definitions = kafkaTopicsProperties.definitions();
        assertFalse(definitions.isEmpty());
    }

    @Nested
    @DisplayName("Topic definition")
    class Topic {

        private static final String TOPIC_NAME = "event";
        private static final String DLT_NAME = "event-dlt";

        KafkaTopicsProperties.Definition definition = kafkaTopicsProperties.definitions().get("event");

        @Test
        @DisplayName("Should load topic name")
        void shouldLoadTopicName() {
            String topic = definition.getTopic();
            assertEquals(TOPIC_NAME, topic);
        }

        @Test
        @DisplayName("Should load dlt name")
        void shouldLoadDltName() {
            String topic = definition.getDlt();
            assertEquals(DLT_NAME, topic);
        }
    }

}