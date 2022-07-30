package fr.knife.estockapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Represent a stock
 */
@Getter
@Setter
@Accessors(chain = true)
public class StockDTO {
    /**
     * The name
     */
    private String name;

    /**
     * The ISIN code
     */
    private String isin;

    /**
     * The value
     */
    private Double value;

    /**
     * The formatted value
     */
    private String formattedValue;
}
