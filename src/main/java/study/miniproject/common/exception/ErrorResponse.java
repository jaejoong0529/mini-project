package study.miniproject.common.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String code,
        String message,
        List<ErrorData> errors,
        LocalDateTime timestamp
) {
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getStatus(),
                errorCode.name(),
                errorCode.getMessage(),
                List.of(),
                LocalDateTime.now()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, List<ErrorData> errors) {
        return new ErrorResponse(
                errorCode.getStatus(),
                errorCode.name(),
                errorCode.getMessage(),
                errors,
                LocalDateTime.now()
        );
    }
}
