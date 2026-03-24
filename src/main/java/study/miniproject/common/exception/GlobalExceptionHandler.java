package study.miniproject.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 커스텀 도메인 예외
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.warn("Business exception: {}", e.getMessage());

        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode));
    }
    // @Valid 요청값 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        List<ErrorData> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toErrorData)
                .toList();

        return ResponseEntity.status(ErrorCode.INVALID_INPUT.getStatus())
                .body(ErrorResponse.of(ErrorCode.INVALID_INPUT, errors));
    }
    // JSON/enum 파싱 실패
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(HttpMessageNotReadableException e) {
        ErrorData errorData = new ErrorData(
                "INVALID_JSON",
                "request",
                "요청 본문(JSON) 형식이 올바르지 않습니다."
        );

        return ResponseEntity.status(ErrorCode.INVALID_JSON.getStatus())
                .body(ErrorResponse.of(ErrorCode.INVALID_JSON, List.of(errorData)));
    }
    // 최종 안전망(500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unhandled exception", e);
        return ResponseEntity.status(ErrorCode.INTERNAL_ERROR.getStatus())
                .body(ErrorResponse.of(ErrorCode.INTERNAL_ERROR));
    }

    private ErrorData toErrorData(FieldError fieldError) {
        return new ErrorData(
                fieldError.getCode(),
                fieldError.getField(),
                fieldError.getDefaultMessage()
        );
    }
}
