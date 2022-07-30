package fr.knife.estockapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Represent a REST error
 */
@Getter
@Setter
@Accessors(chain = true)
public class RestError {
    /**
     * The error key (for translation)
     */
    private String key;

    /**
     * The default message
     */
    private String defaultMessage;

    /**
     * The arguments for translation
     */
    private List<String> args;
}
