package com.dms.pms.error;

import com.dms.pms.error.exception.BusinessException;
import com.dms.pms.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("HandleMethodArgumentNotValidException: ", e);
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST.getStatus(),
                ErrorCode.BAD_REQUEST.getMessage()), HttpStatus.valueOf(ErrorCode.BAD_REQUEST.getStatus()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        log.warn("BusinessException: ", e);

        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(), errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("AccessDeniedException: ", e);

        return new ResponseEntity<>(new ErrorResponse(ErrorCode.INSUFFICIENT_USER_PERMISSIONS.getStatus(),
                ErrorCode.INSUFFICIENT_USER_PERMISSIONS.getMessage()), HttpStatus.valueOf(ErrorCode.INSUFFICIENT_USER_PERMISSIONS.getStatus()));
    }
}
