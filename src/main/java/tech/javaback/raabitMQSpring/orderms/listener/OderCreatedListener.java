package tech.javaback.raabitMQSpring.orderms.listener;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tech.javaback.raabitMQSpring.orderms.listener.DTO.OrderCreatedEvent;
import tech.javaback.raabitMQSpring.orderms.service.OrderService;

import java.util.logging.Logger;

import static tech.javaback.raabitMQSpring.orderms.config.RabbitMQConfig.ORDER_CREATED_QUEUE;

@Component
public class OderCreatedListener {

    private final Logger logger = (Logger) LoggerFactory.getLogger(OderCreatedListener.class);


    private OrderService orderService;

    public void OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    private void listen(Message<OrderCreatedEvent> message) {
        logger.info("Messa consumed {}");
        orderService.save(message.getPayload());
    }
}
