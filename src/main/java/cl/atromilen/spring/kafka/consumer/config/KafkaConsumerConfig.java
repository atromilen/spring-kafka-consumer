package cl.atromilen.spring.kafka.consumer.config;

import cl.atromilen.spring.kafka.consumer.event.Event;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    private final ConfigProperties configProperties;

    @Bean
    public ConsumerFactory<String, Event> consumerFactory() {
        return new DefaultKafkaConsumerFactory(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, configProperties.getBootstrapServers(),
                        ConsumerConfig.GROUP_ID_CONFIG, configProperties.getGroupId(),
                        ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false,
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                        //Prop required both in producer and consumer (same alias "event" but point corresponding class)
                        JsonSerializer.TYPE_MAPPINGS, "event:cl.atromilen.spring.kafka.consumer.event.Event"
                )
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event> listenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(consumerFactory());

        return listenerContainerFactory;
    }

}
