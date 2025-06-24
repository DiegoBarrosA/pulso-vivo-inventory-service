package one.expressdev.pulso_vivo_inventory_service.service;

import static one.expressdev.pulso_vivo_inventory_service.RabbitMQConfig.QUEUE_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Service responsible for receiving messages from a RabbitMQ queue.
 * This is our "Consumer" or "Listener".
 */
@Service
public class RabbitMQConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        RabbitMQConsumerService.class
    );

    /**
     * This method is designated as a listener for messages on a specific queue.
     * The @RabbitListener annotation creates a message listener container under the hood.
     * It will automatically be called when a message arrives on the queue specified.
     *
     * @param message The content of the received message.
     */
    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(String message) {
        LOGGER.info(
            String.format("Received message from %s: %s", QUEUE_NAME, message)
        );
        // Here you can add logic to process the received message.
    }
}
