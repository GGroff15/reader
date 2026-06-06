package datastream.reader.configuration;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.kafka.autoconfigure.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaTopicsProperties.class)
class KafkaConsumerConfiguration {

    private final KafkaTopicsProperties kafkaTopicsProperties;
    private final ConcurrentKafkaListenerContainerFactoryConfigurer configurer;
    private final ConsumerFactory<Object, Object> consumerFactory;

    private final List<ConcurrentMessageListenerContainer<Object, Object>> containers = new ArrayList<>();

    KafkaConsumerConfiguration(KafkaTopicsProperties kafkaTopicsProperties, ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object, Object> consumerFactory) {
        this.kafkaTopicsProperties = kafkaTopicsProperties;
        this.configurer = configurer;
        this.consumerFactory = consumerFactory;
    }

    @Bean
    ApplicationRunner context() {
        return _ -> kafkaTopicsProperties.definitions().entrySet()
                .forEach(definition -> {
                    ConcurrentMessageListenerContainer<Object, Object> container = createContainer(definition);
                    container.start();
                    containers.add(container);
                });
    }

    private ConcurrentMessageListenerContainer<Object, Object> createContainer(Map.Entry<String, KafkaTopicsProperties.Definition> definition) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        KafkaTopicsProperties.Definition topicDefinition = definition.getValue();
        configurer.configure(factory, consumerFactory);
        factory.setConcurrency(topicDefinition.concurrency());
        ConcurrentMessageListenerContainer<Object, Object> container = factory.createContainer(topicDefinition.getTopic());
        container.setupMessageListener((MessageListener<Object, Object>) _ -> {});
        return container;
    }

    @PreDestroy
    void destroy() {
        containers.forEach(ConcurrentMessageListenerContainer::stop);
        containers.clear();
    }
}
