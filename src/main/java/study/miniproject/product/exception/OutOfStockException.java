package study.miniproject.product.exception;

import study.miniproject.common.exception.BusinessException;
import study.miniproject.common.exception.ErrorCode;

public class OutOfStockException extends BusinessException {
    public OutOfStockException(String message) {
        super(ErrorCode.OUT_OF_STOCK, message);
    }

    public static OutOfStockException of(int stock, int quantity) {
        return new OutOfStockException(
                "재고가 부족합니다. 현재 재고=%d, 요청 수량=%d".formatted(stock, quantity)
        );
    }
}
