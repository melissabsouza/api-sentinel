package br.com.fiap.sentinel_api.service.mq;


import br.com.fiap.sentinel_api.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    private String emailQueue;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmail(Object payload) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, payload);

    }
}
