package cl.atromilen.spring.kafka.consumer.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Event<T> {
    private final Instant date = Instant.now();
    private T data;
}
