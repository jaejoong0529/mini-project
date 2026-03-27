package study.miniproject.order.dto.response;

import study.miniproject.order.domain.Order;
import study.miniproject.product.domain.Category;

public record OrderDetailResponse(
        Long orderId,
        Long productId,
        String productName,
        Integer quantity,
        Long totalPrice,
        Category category
) {
    public static OrderDetailResponse from(Order order) {
        return new OrderDetailResponse(
                order.getId(),
                order.getProduct().getId(),
                order.getProduct().getProductName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getProduct().getCategory()
        );
    }
}
