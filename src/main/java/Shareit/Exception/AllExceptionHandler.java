package Shareit.Exception;

import Shareit.Item.ItemValidateException;
import Shareit.User.UserValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class AllExceptionHandler {

    @ExceptionHandler(UserValidateException.class)
    protected ResponseEntity<Object> handleConflict(UserValidateException ex, WebRequest request) {
        log.error("UserValidateException:" + ex.getErrorMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(Map.of("errorMessage", ex.getErrorMessage()));
    }

    @ExceptionHandler(ItemValidateException.class)
    protected ResponseEntity<Object> handleConflict(ItemValidateException ex, WebRequest request) {
        log.error("ItemValidateException:" + ex.getErrorMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(Map.of("errorMessage", ex.getErrorMessage()));
    }

}
