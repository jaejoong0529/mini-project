package study.miniproject.common.exception;

public record ErrorData(
        String errorCode,
        String field,
        String message
) {
}
