package Shareit.Exception;

import Shareit.Item.ItemValidateException;
import Shareit.User.UserValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class AllExceptionHandler {

    private final Logger logger =
            LoggerFactory.getLogger(AllExceptionHandler.class);

    @ExceptionHandler(UserValidateException.class)
    protected ResponseEntity<Object> handleConflict(UserValidateException ex, WebRequest request) {
        logger.error("UserValidateException:" + ex.getErrorMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(Map.of("errorMessage", ex.getErrorMessage()));
    }

    @ExceptionHandler(ItemValidateException.class)
    protected ResponseEntity<Object> handleConflict(ItemValidateException ex, WebRequest request) {
        logger.error("ItemValidateException:" + ex.getErrorMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(Map.of("errorMessage", ex.getErrorMessage()));
    }

}
