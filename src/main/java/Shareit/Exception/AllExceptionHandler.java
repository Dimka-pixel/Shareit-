package Shareit.Exception;

import Shareit.Item.ItemValidateException;
import Shareit.User.UserValidateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(UserValidateException.class)
    protected ResponseEntity<Object> handleConflict(UserValidateException ex, WebRequest request) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(Map.of("errorMessage", ex.getErrorMessage()));
    }

    @ExceptionHandler(ItemValidateException.class)
    protected ResponseEntity<Object> handleConflict(ItemValidateException ex, WebRequest request) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(Map.of("errorMessage", ex.getErrorMessage()));
    }

}
