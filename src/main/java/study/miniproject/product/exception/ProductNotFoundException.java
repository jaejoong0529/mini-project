package study.miniproject.product.exception;

import study.miniproject.common.exception.BusinessException;
import study.miniproject.common.exception.ErrorCode;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
