package study.miniproject.product.exception;

import study.miniproject.common.exception.BusinessException;
import study.miniproject.common.exception.ErrorCode;

public class InvalidStockQuantityException extends BusinessException {
    public InvalidStockQuantityException(String message) {
        super(ErrorCode.INVALID_INPUT, message);
    }

    public static InvalidStockQuantityException of(int quantity) {
        return new InvalidStockQuantityException("차감 수량은 1개 이상이어야 합니다. quantity=" + quantity);
    }
}
