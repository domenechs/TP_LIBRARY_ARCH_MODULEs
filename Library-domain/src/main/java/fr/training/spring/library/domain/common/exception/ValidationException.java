package fr.training.spring.library.domain.common.exception;

public class ValidationException extends BusinessException{

    public ValidationException(String message, String errorCode) {
        super(message, errorCode);
    }
}
