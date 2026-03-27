package study.miniproject.order.dto.response;

import org.springframework.data.domain.Page;
import study.miniproject.order.domain.Order;

import java.util.List;

public record OrderListResponse (
        List<OrderDetailResponse> content,
        long totalElements,
        int totalPages,
        int page,
        int size,
        boolean last
){
    public static OrderListResponse from(Page<Order> page){
        return  new OrderListResponse(
                page.getContent().stream().map(OrderDetailResponse::from).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.isLast()
        );
    }
}
