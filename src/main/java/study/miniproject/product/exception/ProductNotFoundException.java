package study.miniproject.product.exception;

import study.miniproject.common.exception.BusinessException;
import study.miniproject.common.exception.ErrorCode;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(String message) {
        super(ErrorCode.PRODUCT_NOT_FOUND, message);
    }

    public static ProductNotFoundException of(Long productId) {
        return new ProductNotFoundException("상품을 찾을 수 없습니다. productId=" + productId);
    }
}
