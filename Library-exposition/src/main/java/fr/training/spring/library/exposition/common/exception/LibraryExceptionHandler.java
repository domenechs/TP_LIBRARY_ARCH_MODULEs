package fr.training.spring.library.exposition.common.exception;

import fr.training.spring.library.domain.common.exception.NotFoundLibraryException;
import fr.training.spring.library.domain.common.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class LibraryExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value={NotFoundLibraryException.class})
    public ResponseEntity<Object> handleNotFoundLibraryException(NotFoundLibraryException ex){
        return new ResponseEntity<>(ex.getLocalizedMessage()+" "+ex.getErrorCode(),HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value={ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException ex){
        return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
