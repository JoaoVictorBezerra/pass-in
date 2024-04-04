package rocketseat.com.passin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rocketseat.com.passin.domain.attendee.exceptions.AttendeeAlreadyRegisteredException;
import rocketseat.com.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import rocketseat.com.passin.domain.check_in.exceptions.CheckInAlreadyDoneException;
import rocketseat.com.passin.domain.event.exceptions.EventFullException;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passin.dtos.ValidationErrorsDTO;
import rocketseat.com.passin.dtos.exception.ExceptionHandlerDTO;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionHandlerDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(AttendeeAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleAttendeeAlreadyRegistered(AttendeeAlreadyRegisteredException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionHandlerDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleEventFull(EventFullException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionHandlerDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleEventNotFound(AttendeeNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionHandlerDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(CheckInAlreadyDoneException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleCheckInAlreadyDone(CheckInAlreadyDoneException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionHandlerDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorsDTO(errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionHandlerDTO("Invalid JSON payload: " + ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
