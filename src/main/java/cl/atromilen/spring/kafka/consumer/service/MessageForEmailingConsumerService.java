package cl.atromilen.spring.kafka.consumer.service;

import cl.atromilen.spring.kafka.consumer.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageForEmailingConsumerService {

    @KafkaListener(
            topics = "${spring.kafka.consumer.topic-name}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consumerMessageForEmailing(Event message){
        log.info("Read new message: {}", message);
    }
}
