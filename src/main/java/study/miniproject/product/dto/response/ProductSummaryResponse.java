package study.miniproject.product.dto.response;

import study.miniproject.product.domain.Product;

public record ProductSummaryResponse(
        Long productId,
        String productName,
        Long price
) {
    public static ProductSummaryResponse from(Product product) {
        return new ProductSummaryResponse(
                product.getId(),
                product.getProductName(),
                product.getPrice()
        );
    }
}
