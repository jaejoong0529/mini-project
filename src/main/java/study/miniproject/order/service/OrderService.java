package study.miniproject.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.miniproject.order.domain.Order;
import study.miniproject.order.dto.request.OrderCreateRequest;
import study.miniproject.order.dto.response.OrderCreateResponse;
import study.miniproject.order.repository.OrderRepository;
import study.miniproject.product.domain.Product;
import study.miniproject.product.exception.ProductNotFoundException;
import study.miniproject.product.repository.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderCreateResponse create(OrderCreateRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> ProductNotFoundException.of(request.productId()));
        Order order = createOrder(product, request);
        Order savedOrder = orderRepository.save(order);
        return OrderCreateResponse.from(savedOrder);
    }

    private Order createOrder(Product product, OrderCreateRequest request) {
        return Order.createOrder(product, request.quantity());
    }
}
