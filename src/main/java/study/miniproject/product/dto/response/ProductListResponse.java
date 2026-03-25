package study.miniproject.product.dto.response;

import org.springframework.data.domain.Page;
import study.miniproject.product.domain.Product;

import java.util.List;

public record ProductListResponse (
        List<ProductSummaryResponse> content,
        long totalElements,
        int totalPages,
        int page,
        int size,
        boolean last
){
    public static ProductListResponse from(Page<Product> page) {
        return new ProductListResponse(
                page.getContent().stream().map(ProductSummaryResponse::from).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.isLast()
        );
    }
}
