package cl.atromilen.spring.kafka.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Data
public class ConfigProperties {
    private String bootstrapServers;
    private String topicName;
    private String groupId;
    private String enableAutoCommit;
//    private Integer sessionTimeoutMs;
}
