package com.example.contacts.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DefaultExceptionMapper {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionMapper.class);

    @Autowired
    private MessageSource msgSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorMessage processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        logger.error("Validation error on field " + field, ex);
        ErrorMessage msg = new ErrorMessage();
        msg.setError("Validation failed for field: " + field);
        msg.setDescription(ex.getMessage());
        return msg;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage badRequest(Exception e) {
        logger.error("Request raised a IllegalArgumentException", e);
        ErrorMessage msg = new ErrorMessage();
        msg.setError("Bad Request");
        msg.setDescription(e.getMessage());
        return msg;
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage conflict(Exception e) {
        logger.error("Request raised a ConflictException", e.getCause());
        ErrorMessage msg = new ErrorMessage();
        msg.setError("Conflict");
        msg.setDescription(e.getMessage());
        return msg;
    }
}
