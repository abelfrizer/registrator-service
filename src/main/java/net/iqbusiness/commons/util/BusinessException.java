package net.iqbusiness.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {
    protected final List<String> errors = new ArrayList();

    public BusinessException() {
    }

    public BusinessException(List<String> errors) {
        super("Database interaction failed due to listed errors");
        this.errors.addAll(errors);
    }

    public BusinessException(String message) {
        super(message);
        this.errors.add(message);
    }

    public BusinessException(String message, List<String> errors) {
        super(message);
        this.errors.addAll(errors);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errors.add(message);
    }

    public BusinessException(String message, Throwable cause, List<String> errors) {
        super(message, cause);
        this.errors.addAll(errors);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public BusinessException addError(String error) {
        this.errors.add(error);
        return this;
    }

    public BusinessException addErrors(List<String> errors) {
        this.errors.addAll(errors);
        return this;
    }

    public String getMessage() {
        StringBuilder message = new StringBuilder();
        if (StringUtils.isNotBlank(super.getMessage())) {
            message.append(super.getMessage());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String errorsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.errors);
            message.append("\n'ERRORS':").append(errorsString);
        } catch (JsonProcessingException var4) {
        }

        return message.toString();
    }
}
