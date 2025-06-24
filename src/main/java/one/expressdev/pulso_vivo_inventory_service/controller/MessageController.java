package one.expressdev.pulso_vivo_inventory_service.controller;

import one.expressdev.pulso_vivo_inventory_service.service.RabbitMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A simple REST controller to expose an endpoint for sending messages.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final RabbitMQProducerService producerService;

    @Autowired
    public MessageController(RabbitMQProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * An HTTP POST endpoint to send a message.
     * The message content is taken from the request body.
     *
     * @param message The string message from the HTTP request body.
     * @return A confirmation response.
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ successfully!");
    }
}
