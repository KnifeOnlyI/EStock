package fr.knife.estockapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * The base class for all managed exceptions
 */
@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class BaseException extends RuntimeException {
    /**
     * The HTTP status
     */
    private final HttpStatus status;

    /**
     * The error key (for translation)
     */
    private final String key;

    /**
     * The default message
     */
    private final String defaultMessage;

    /**
     * The internal message (not send to API client)
     */
    private final String internalMessage;

    /**
     * The arguments for translation
     */
    private final List<String> args;
}
