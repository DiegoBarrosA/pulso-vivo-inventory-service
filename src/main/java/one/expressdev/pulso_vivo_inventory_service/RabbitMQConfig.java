package one.expressdev.pulso_vivo_inventory_service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the RabbitMQ components (Queue, Exchange, and Binding).
 * Spring AMQP will automatically declare these on the RabbitMQ broker if they don't already exist.
 */
@Configuration
public class RabbitMQConfig {

    // Define constants for the names to avoid typos and centralize configuration.
    public static final String EXCHANGE_NAME = "my_topic_exchange";
    public static final String QUEUE_NAME = "my_queue";
    public static final String ROUTING_KEY = "my.routing.key";

    /**
     * Declares a durable queue.
     * @return A Queue bean.
     */
    @Bean
    public Queue queue() {
        // A durable queue will survive a broker restart.
        return new Queue(QUEUE_NAME, true);
    }

    /**
     * Declares a durable topic exchange.
     * @return A TopicExchange bean.
     */
    @Bean
    public TopicExchange exchange() {
        // A durable exchange will also survive a broker restart.
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * Binds the queue to the exchange with a specific routing key.
     * Messages sent to the exchange with this routing key will be routed to our queue.
     * @param queue The queue bean.
     * @param exchange The exchange bean.
     * @return A Binding bean.
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
