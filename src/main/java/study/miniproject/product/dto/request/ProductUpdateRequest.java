package study.miniproject.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import study.miniproject.product.domain.Category;

public record ProductUpdateRequest(
        @Size(max = 100)
        String productName,

        @Size(max = 500)
        String description,

        @Min(value = 100, message = "상품 가격은 최소 100원 이상이어야 합니다.")
        Long price,

        Category category
) {
}
