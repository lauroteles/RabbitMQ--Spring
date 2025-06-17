package tech.javaback.raabitMQSpring.orderms.listener.DTO;

import java.math.BigDecimal;

public record OrderItemEvent(String produto,
                             int quantidade,
                             BigDecimal preco) {
}
