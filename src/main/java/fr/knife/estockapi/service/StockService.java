package fr.knife.estockapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The service to manage stocks
 */
@Service
public class StockService {
    /**
     * The service to manage HTTP operations
     */
    private final HTTPService httpService;

    /**
     * The service to manage HTML operations
     */
    private final HTMLService htmlService;

    /**
     * The base URL of where to fetch the value
     */
    @Value("${app.stock.base-url}")
    private String stockBaseUrl;

    /**
     * Create a new service to manage stocks
     *
     * @param httpService The service to manage HTTP operations
     * @param htmlService The service to manage HTML operations
     */
    public StockService(HTTPService httpService, HTMLService htmlService) {
        this.httpService = httpService;
        this.htmlService = htmlService;
    }

    /**
     * Get the value of the specified stock identified by his ISIN code
     *
     * @param isin The ISIN code of stock
     *
     * @return The value
     */
    public Double getValue(String isin) {
        String html = this.httpService.fetch(String.format("%s/%s", this.stockBaseUrl, isin));

        return this.htmlService.getStockValue(html);
    }
}
