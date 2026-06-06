package datastream.reader.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "topics")
public class KafkaTopicsProperties {

    /**
     * Topics configured to create Kafka consumers automatically
     */
    private Map<String, Definition> definitions;

    public KafkaTopicsProperties(Map<String, Definition> definitions) {
        this.definitions = definitions;
    }

    public Map<String, Definition> definitions() {
        return definitions;
    }

    /**
     * Define topic configuration to create Kafka consumers automatically
     */
    public static class Definition {

        /**
         * Define topic's name
         */
        @NotNull
        @NotBlank
        private String topic;
        /**
         * Define topic's dead letter topic name
         */
        @NotNull
        @NotBlank
        private String dlt;
        /**
         * Define concurrence to consume records from Kafka
         */
        private int concurrency = 1;

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public void setDlt(String dlt) {
            this.dlt = dlt;
        }

        public void setConcurrency(int concurrency) {
            this.concurrency = concurrency;
        }

        public String getTopic() {
            return topic;
        }

        public String getDlt() {
            return dlt;
        }

        public int concurrency() {
            return concurrency;
        }
    }
}
