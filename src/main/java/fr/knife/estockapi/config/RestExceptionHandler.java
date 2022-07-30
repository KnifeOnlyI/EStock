package fr.knife.estockapi.config;

import fr.knife.estockapi.dto.RestError;
import fr.knife.estockapi.exception.InternalServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The exception handler
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * The logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handle all unknow errors
     *
     * @return The unknow errors
     */
    @ExceptionHandler(value = Throwable.class)
    protected ResponseEntity<RestError> handleUnknow(Throwable e) {
        LOG.error(e.getMessage());

        return new ResponseEntity<>(
            new RestError().setKey("error.server.unknow").setDefaultMessage("Unknow server error"),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Handle all unknow errors but managed by server
     *
     * @return The unknow errors
     */
    @ExceptionHandler(value = InternalServerException.class)
    protected ResponseEntity<RestError> handleInternalServerException(InternalServerException e) {
        LOG.error(e.getInternalMessage(), e.getMessage());

        return new ResponseEntity<>(
            new RestError().setKey(e.getKey()).setDefaultMessage(e.getDefaultMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
