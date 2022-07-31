package fr.knife.estockapi.service;

import fr.knife.estockapi.domain.StockIndexEntity;
import fr.knife.estockapi.domain.TrackedStockEntity;
import fr.knife.estockapi.repository.StockIndexRepository;
import fr.knife.estockapi.repository.TrackedStockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * The repository to manage tracked stocks
     */
    private final TrackedStockRepository trackedStockRepository;

    /**
     * The repository to manage stock indexes
     */
    private final StockIndexRepository stockIndexRepository;

    /**
     * The base URL of where to fetch the value
     */
    @Value("${app.stock.base-url}")
    private String stockBaseUrl;

    /**
     * Create a new service to manage stocks
     *
     * @param httpService            The service to manage HTTP operations
     * @param htmlService            The service to manage HTML operations
     * @param trackedStockRepository The repository to manage tracked stocks
     * @param stockIndexRepository   The repository to manage stock indexes
     */
    public StockService(
        HTTPService httpService,
        HTMLService htmlService,
        TrackedStockRepository trackedStockRepository,
        StockIndexRepository stockIndexRepository
    ) {
        this.httpService = httpService;
        this.htmlService = htmlService;
        this.trackedStockRepository = trackedStockRepository;
        this.stockIndexRepository = stockIndexRepository;
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

    /**
     * Get all tracked stocks
     *
     * @return The result
     */
    public List<TrackedStockEntity> getAllTracked() {
        return this.trackedStockRepository.findAll();
    }

    /**
     * Get all stock indexes
     *
     * @return The result
     */
    public List<StockIndexEntity> getAllIndexes() {
        return this.stockIndexRepository.findAll();
    }
}
