package com.stackhack.timesheet.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeSheetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    private final String code;

    private final Map<String, List<String>> fieldErrors;

    public TimeSheetException(HttpStatus httpStatus, String code,Throwable cause) {
        super(code, cause);
        this.httpStatus = httpStatus;
        this.code = code;
        this.fieldErrors = new HashMap<>();
    }

    public TimeSheetException(HttpStatus httpStatus, String code) {
        super(code);
        this.httpStatus = httpStatus;
        this.code = code;
        this.fieldErrors = new HashMap<>();
    }

    public Map<String, List<String>> getFieldErrors() {
        return fieldErrors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

}
