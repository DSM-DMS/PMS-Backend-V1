package com.dms.pms.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    BAD_REQUEST(400, "Bad Request: Invalid Parameter."),
    UNAUTHORIZED(401, "UnAuthorized: Please check your authentication"),
    USER_NOT_FOUND(404, "Not Found: Parent not found."),
    USER_DUPLICATION(409, "Conflict: User already exists.");

    private final int status;
    private final String message;
}
