package tech.javaback.raabitMQSpring.orderms.listener.DTO;

import java.util.List;

public record OrderCreatedEvent (Long codigoPedido,
                                 Long codigoCliente,
                                 List<OrderItemEvent> items){
}
