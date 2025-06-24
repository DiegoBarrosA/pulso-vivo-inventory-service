package one.expressdev.pulso_vivo_inventory_service.service;

import one.expressdev.pulso_vivo_inventory_service.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for sending messages to RabbitMQ.
 * This is our "Producer".
 */
@Service
public class RabbitMQProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        RabbitMQProducerService.class
    );

    // RabbitTemplate is the core Spring AMQP class for sending and receiving messages.
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Sends a message to the configured exchange with the configured routing key.
     * @param message The message content as a String.
     */
    public void sendMessage(String message) {
        LOGGER.info(String.format("Sending message -> %s", message));
        // The convertAndSend method converts the message to a Spring AMQP Message
        // and sends it to the specified exchange with the given routing key.
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            RabbitMQConfig.ROUTING_KEY,
            message
        );
    }
}
