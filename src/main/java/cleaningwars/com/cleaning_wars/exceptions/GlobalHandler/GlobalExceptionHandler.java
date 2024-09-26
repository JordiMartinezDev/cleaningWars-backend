package cleaningwars.com.cleaning_wars.exceptions.GlobalHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import cleaningwars.com.cleaning_wars.exceptions.EmailAlreadyRegistered;
import cleaningwars.com.cleaning_wars.exceptions.EmptyInput;
import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.exceptions.UserNotFound;
import cleaningwars.com.cleaning_wars.exceptions.responses.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    ErrorResponse errorResponse;

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<Object> EntityNotFoundHandler(EntityNotFound ex) {
        errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyRegistered.class)
    public ResponseEntity<Object> EmailAlreadyRegisteredHandler(EmailAlreadyRegistered ex) {
        errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT); 
    }
   
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> UserNotFoundHandler(UserNotFound ex){
        errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyInput.class)
    public ResponseEntity<Object> EmptyInputHandler(EmptyInput ex){
        errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
