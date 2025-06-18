package tech.javaback.raabitMQSpring.orderms.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.javaback.raabitMQSpring.orderms.controller.dto.ApiResponse;
import tech.javaback.raabitMQSpring.orderms.controller.dto.OrderResponse;
import tech.javaback.raabitMQSpring.orderms.controller.dto.PaginationResponse;
import tech.javaback.raabitMQSpring.orderms.service.OrderService;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@PathVariable("customerId") Long customerId,
                                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "pageSize",defaultValue = "10")Integer pagesize) {

        var body = orderService.findAllByCustomerId(customerId, PageRequest.of(page,pagesize));

        return ResponseEntity.ok(new ApiResponse<> (
                body.getContent(),
                PaginationResponse.fromPage(body)
        ));
    }

}
