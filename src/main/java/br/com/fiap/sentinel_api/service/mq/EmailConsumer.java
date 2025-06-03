package br.com.fiap.sentinel_api.service.mq;


import br.com.fiap.sentinel_api.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(EmailPayload message) {
        System.out.println("📨 Message received from the queue: " + message);

        String emailTo = message.getTo();
        String subject = message.getSubject();
        String body = message.getBody();

        emailService.sendEmail(emailTo, subject, body);
    }
}
