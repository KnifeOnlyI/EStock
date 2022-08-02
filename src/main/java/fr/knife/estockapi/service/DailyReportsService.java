package fr.knife.estockapi.service;

import fr.knife.estockapi.domain.StockIndexEntity;
import fr.knife.estockapi.domain.TrackedStockEntity;
import fr.knife.estockapi.dto.StockDTO;
import fr.knife.estockapi.dto.StockIndexDTO;
import fr.knife.estockapi.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * The service to manage daily reports
 */
@Service
public class DailyReportsService {
    /**
     * The service to manage emails
     */
    private final EmailService emailService;

    /**
     * The service to manage stocks
     */
    private final StockService stockService;

    /**
     * The template engine
     */
    private final SpringTemplateEngine templateEngine;

    /**
     * The email of receiver
     */
    @Value("${app.mail.receiver}")
    private String receiver;

    /**
     * The stock daily report subject
     */
    @Value("${app.mail.stock.daily-report.subject}")
    private String stockDailyReportSubject;

    /**
     * Create a new instance
     *
     * @param emailService   The service to manage emails
     * @param stockService   The service to manage stocks
     * @param templateEngine The template engine
     */
    public DailyReportsService(
        EmailService emailService,
        StockService stockService,
        SpringTemplateEngine templateEngine
    ) {
        this.emailService = emailService;
        this.stockService = stockService;
        this.templateEngine = templateEngine;
    }

    /**
     * Build a list of stock index DTO corresponding to the specified stock indexes
     *
     * @param stockIndexes The stock indexes to check
     * @param stocksDTO    The stocks DTO that contains the values
     *
     * @return The corresponding stock index DTO list
     */
    private static List<StockIndexDTO> buildStockIndexDTOList(
        List<StockIndexEntity> stockIndexes,
        List<StockDTO> stocksDTO
    ) {
        List<StockIndexDTO> stockIndexList = new ArrayList<>();

        stockIndexes.forEach((StockIndexEntity stockIndex) -> {
            Double value = 0.0;

            for (TrackedStockEntity stock : stockIndex.getStocks()) {
                value += stocksDTO
                    .stream()
                    .filter(stockDTO -> stockDTO.getId().equals(stock.getId()))
                    .findFirst()
                    .orElseThrow()
                    .getValue();
            }

            StockIndexDTO stockIndexDTO = new StockIndexDTO()
                .setName(stockIndex.getName())
                .setFormattedValue(NumberUtil.formatToPoints(value));

            stockIndexList.add(stockIndexDTO);
        });

        return stockIndexList;
    }

    /**
     * The schedule for stock daily reports
     */
    public void sendStockDailyReports() {
        List<TrackedStockEntity> trackedStocks = this.stockService.getAllTracked();

        if (!trackedStocks.isEmpty()) {
            List<StockDTO> stocks = this.buildStockDTOList(trackedStocks);
            List<StockIndexDTO> stockIndexes = DailyReportsService.buildStockIndexDTOList(
                this.stockService.getAllIndexes(),
                stocks
            );

            this.emailService.send(
                this.receiver,
                this.stockDailyReportSubject,
                this.buildStockDailyReport(stocks, stockIndexes)
            );
        }
    }

    /**
     * Build the stock daily report
     *
     * @param stockIndexes  The tracked stocks
     * @param trackedStocks The stock indexes
     *
     * @return The stock daily report
     */
    private String buildStockDailyReport(List<StockDTO> trackedStocks, List<StockIndexDTO> stockIndexes) {
        Context context = new Context();

        context.setVariable("stocks", trackedStocks);
        context.setVariable("stockIndexes", stockIndexes);

        return this.templateEngine.process("stock-daily-reports.html", context);
    }

    /**
     * Build a list of stock DTO corresponding to the specified tracked stocks
     *
     * @param trackedStocks The tracked stocks to check
     *
     * @return The corresponding stock DTO list
     */
    private List<StockDTO> buildStockDTOList(List<TrackedStockEntity> trackedStocks) {
        List<StockDTO> stockDTOList = new ArrayList<>();

        trackedStocks.forEach((TrackedStockEntity trackedStock) -> {
            double value = this.stockService.getValue(trackedStock.getIsin());

            StockDTO stock = new StockDTO()
                .setId(trackedStock.getId())
                .setName(trackedStock.getName())
                .setIsin(trackedStock.getIsin())
                .setValue(value)
                .setFormattedValue(NumberUtil.formatToAccounting(value));

            stockDTOList.add(stock);
        });

        stockDTOList.sort((o1, o2) -> (int) (o2.getValue() - o1.getValue()));

        return stockDTOList;
    }
}
