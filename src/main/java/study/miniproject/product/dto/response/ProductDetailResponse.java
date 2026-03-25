package study.miniproject.product.dto.response;

import study.miniproject.product.domain.Category;
import study.miniproject.product.domain.Product;

public record ProductDetailResponse (
        Long productId,
        String productName,
        String description,
        Long price,
        Category category
){
    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
