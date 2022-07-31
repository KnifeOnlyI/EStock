package fr.knife.estockapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Represent a stock index
 */
@Getter
@Setter
@Accessors(chain = true)
public class StockIndexDTO {
    /**
     * The name
     */
    private String name;

    /**
     * The formatted value
     */
    private String formattedValue;
}
