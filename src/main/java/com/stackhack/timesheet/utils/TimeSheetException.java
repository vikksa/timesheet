package com.stackhack.timesheet.utils;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class TimeSheetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    HttpStatus httpStatus;

    String code;

    List<Object> data;

    Map<String, List<String>> fieldErrors;

    public TimeSheetException() {
    }

    public TimeSheetException(HttpStatus httpStatus, String code, List<Object> data, Throwable cause) {
        super(code, cause);
        this.httpStatus = httpStatus;
        this.code = code;
        this.data = data;
    }

    public TimeSheetException(HttpStatus httpStatus, String code, List<Object> data) {
        super(code);
        this.httpStatus = httpStatus;
        this.code = code;
        this.data = data;
    }

    public TimeSheetException(HttpStatus httpStatus, String code) {
        super(code);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public Map<String, List<String>> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, List<String>> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
