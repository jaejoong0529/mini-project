package study.miniproject.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.miniproject.order.dto.request.OrderCreateRequest;
import study.miniproject.order.dto.response.OrderCreateResponse;
import study.miniproject.order.dto.response.OrderDetailResponse;
import study.miniproject.order.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateResponse> createOrder(
            @Valid @RequestBody OrderCreateRequest request) {
        OrderCreateResponse response = orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponse> getOrder(
            @PathVariable Long orderId
    ) {
        OrderDetailResponse response = orderService.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
