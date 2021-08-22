package com.stackhack.timesheet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    private PropertyNamingStrategy.PropertyNamingStrategyBase propertyNamingStrategy;

    public GlobalExceptionHandler(ApplicationContext applicationContext) {
        try {
            final ObjectMapper objectMapper = (ObjectMapper) applicationContext.getBean("jacksonObjectMapper");
            this.propertyNamingStrategy = (PropertyNamingStrategy.PropertyNamingStrategyBase)
                    objectMapper.getPropertyNamingStrategy();
        } catch (Exception e) {
            logger.error("a valid PropertyNamingStrategyBase wasn't found in application context", e);
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        logger.debug(status.getDeclaringClass()
                + " raised a "
                + status
                + " error: "
                + status.getReasonPhrase());

        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());

        final Map<String, List<String>> fieldErrors = new HashMap<String, List<String>>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String field = fieldError.getField();
            if (this.propertyNamingStrategy != null) {
                field = this.propertyNamingStrategy.translate(field);
            }

            List<String> errors = fieldErrors.getOrDefault(field, new ArrayList<>());
            errors.add(fieldError.getDefaultMessage());
            fieldErrors.put(field, errors);
        }
        response.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(response, headers, status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ex.getStatus().value());
        response.setCode(ex.getReason());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        logger.debug("access denied cause: " + ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        errorResponse.setCode("CW_SYS:ERR_RESOURCE_ACCESS_DENIED");
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TimeSheetException.class)
    public final ResponseEntity<ErrorResponse> handleClowreException(TimeSheetException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ex.getHttpStatus().value());
        response.setCode(ex.getCode());
        response.setData(ex.getData());
        response.setFieldErrors(ex.getFieldErrors());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        final UUID reference = UUID.randomUUID();
        logger.error("REF [" + reference + "] > Uncaught error: " + ex.getMessage(), ex);

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setCode("ERR_INTERNAL");
        response.setData(null);
        response.setTraceId(reference.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
