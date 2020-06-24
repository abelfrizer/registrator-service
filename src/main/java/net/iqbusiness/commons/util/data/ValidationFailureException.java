package net.iqbusiness.commons.util.data;

import net.iqbusiness.commons.util.BusinessException;

import java.util.List;

public class ValidationFailureException extends BusinessException {
    public ValidationFailureException() {
        super("Validation failure occured");
    }

    public ValidationFailureException(List<String> errors) {
        super(errors);
    }

    public ValidationFailureException(String message) {
        super(message);
    }

    public ValidationFailureException(String message, List<String> errors) {
        super(message, errors);
    }

    public ValidationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationFailureException(String message, Throwable cause, List<String> errors) {
        super(message, cause, errors);
    }

    public ValidationFailureException(Throwable cause) {
        super(cause);
    }

    public ValidationFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidationFailureException addErrors(List<String> errors) {
        return (ValidationFailureException)super.addErrors(errors);
    }

    public ValidationFailureException addError(String error) {
        return (ValidationFailureException)super.addError(error);
    }
}
