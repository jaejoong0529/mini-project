package study.miniproject.order.dto.request;

import jakarta.validation.constraints.*;

public record OrderCreateRequest(
        @NotNull
        Long productId,

        @NotNull
        @Min(1)
        Integer quantity
) {
}
