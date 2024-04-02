package rocketseat.com.passin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passin.dtos.exception.ExceptionHandlerDTO;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionHandlerDTO(exception.getMessage(), 404));
    }
}
