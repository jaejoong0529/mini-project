package study.miniproject.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import study.miniproject.product.domain.Category;

public record ProductCreateRequest(
        @NotBlank(message = "상품 이름은 필수입니다.")
        @Size(max = 100)
        String productName,

        @NotBlank(message = "상품 설명은 필수입니다.")
        @Size(max = 500)
        String description,

        @NotNull(message = "상품 가격은 필수입니다.")
        @Min(value = 100, message = "상품 가격은 최소 100원 이상이어야 합니다.")
        Long price,

        @NotNull(message = "상품 카테고리는 필수입니다.")
        Category category
) {
}
