package study.miniproject.order.dto.response;

import study.miniproject.order.domain.Order;

public record OrderCreateResponse(
        Long orderId,
        String productName,
        Integer quantity,
        Long totalPrice
) {
    public static OrderCreateResponse from(Order order) {
        return new OrderCreateResponse(
                order.getId(),
                order.getProduct().getProductName(),
                order.getQuantity(),
                order.getTotalPrice()
        );
    }
}
