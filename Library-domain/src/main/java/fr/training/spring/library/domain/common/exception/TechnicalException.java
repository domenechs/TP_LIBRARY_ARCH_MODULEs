package fr.training.spring.library.domain.common.exception;


public class TechnicalException extends RuntimeException{
    public TechnicalException(Exception e) {
        super(e);
    }
}
