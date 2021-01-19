package fr.training.spring.library.domain.common.exception;

public class NotFoundLibraryException extends BusinessException {

    public NotFoundLibraryException(String message, String errorCode) {
        super(message, errorCode);
    }
}
