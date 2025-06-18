package tech.javaback.raabitMQSpring.orderms.controller.dto;

import jdk.jshell.spi.ExecutionControl;
import tech.javaback.raabitMQSpring.orderms.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse (Long orderId,
                             Long customerId,
                             BigDecimal total) {

    public static OrderResponse fromEntity(OrderEntity entity) {
        return new OrderResponse(entity.getOrderId(), entity.getCustomerId(),entity.getTotal());
    }
}
