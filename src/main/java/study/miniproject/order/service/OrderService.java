package study.miniproject.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.miniproject.order.domain.Order;
import study.miniproject.order.dto.request.OrderCreateRequest;
import study.miniproject.order.dto.response.OrderCreateResponse;
import study.miniproject.order.dto.response.OrderDetailResponse;
import study.miniproject.order.dto.response.OrderListResponse;
import study.miniproject.order.repository.OrderRepository;
import study.miniproject.product.domain.Product;
import study.miniproject.product.repository.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderCreateResponse create(OrderCreateRequest request) {
        log.debug("주문 생성 시작 - productId: {}, quantity: {}", request.productId(), request.quantity());

        Product product = productRepository.getByIdOrThrow(request.productId());
        product.decreaseStock(request.quantity());
        Order order = createOrder(product, request);
        Order savedOrder = orderRepository.save(order);

        log.info("주문 생성 완료 - orderId: {}", savedOrder.getId());
        return OrderCreateResponse.from(savedOrder);
    }

    @Transactional(readOnly = true)
    public OrderDetailResponse getOrder(Long orderId) {
        Order order = orderRepository.getByIdOrThrow(orderId);
        return OrderDetailResponse.from(order);
    }

    @Transactional(readOnly = true)
    public OrderListResponse getOrders(Pageable pageable) {
        Page<Order> page = orderRepository.findAllWithProduct(pageable);
        return OrderListResponse.from(page);
    }

    private Order createOrder(Product product, OrderCreateRequest request) {
        return Order.createOrder(product, request.quantity());
    }
}
