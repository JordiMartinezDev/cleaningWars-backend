package cleaningwars.com.cleaning_wars.exceptions.GlobalHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.exceptions.responses.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    ErrorResponse errorResponse;

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFound ex) {
        errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Add other ExceptionHandlers..

    // @ExceptionHandler({TheClass}.class)
    // public ResponseEntity<Object> exceptionDesiredName({TheClass} ex) {
    //     errorResponse = new ErrorResponse(ex.getMessage());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    // }
}
