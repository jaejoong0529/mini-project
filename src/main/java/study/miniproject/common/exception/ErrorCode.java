package study.miniproject.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 공통
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "요청값 검증에 실패했습니다."),
    INVALID_JSON(HttpStatus.BAD_REQUEST, "요청 본문(JSON) 형식이 올바르지 않습니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),

    // 상품 (PRODUCT)
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),

    // 주문 (ORDER)
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다.");

    private final int status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
