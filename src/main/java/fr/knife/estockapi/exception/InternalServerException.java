package fr.knife.estockapi.exception;

import org.springframework.http.HttpStatus;

/**
 * The exception for all server errors
 */
public class InternalServerException extends BaseException {
    /**
     * Create a new instance
     */
    public InternalServerException(String internalMessage) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "error.server.unknow", "Unknow server error", internalMessage, null);
    }
}
