package study.miniproject.order.exception;

import study.miniproject.common.exception.BusinessException;
import study.miniproject.common.exception.ErrorCode;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(String message) {
        super(ErrorCode.ORDER_NOT_FOUND, message);
    }

    public static OrderNotFoundException of(Long orderId) {
        return new OrderNotFoundException("주문을 찾을 수 없습니다. orderId=" + orderId);
    }
}
