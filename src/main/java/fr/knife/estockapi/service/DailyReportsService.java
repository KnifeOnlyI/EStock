package fr.knife.estockapi.service;

import fr.knife.estockapi.domain.TrackedStockEntity;
import fr.knife.estockapi.dto.StockDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
     * The schedule for stock daily reports
     */
    public void sendStockDailyReports() {
        List<TrackedStockEntity> trackedStocks = this.stockService.getAllTracked();

        if (!trackedStocks.isEmpty()) {
            this.emailService.send(
                this.receiver,
                this.stockDailyReportSubject,
                this.buildStockDailyReport(this.buildStockDTOList(trackedStocks))
            );
        }
    }

    /**
     * Build the stock daily report
     *
     * @param trackedStocks The tracked stocks
     *
     * @return The stock daily report
     */
    private String buildStockDailyReport(List<StockDTO> trackedStocks) {
        Context context = new Context();

        context.setVariable("stocks", trackedStocks);

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
        List<StockDTO> stocks = new ArrayList<>();

        trackedStocks.forEach((TrackedStockEntity trackedStock) -> {
            double value = this.stockService.getValue(trackedStock.getIsin());

            StockDTO stock = new StockDTO()
                .setName(trackedStock.getName())
                .setIsin(trackedStock.getIsin())
                .setValue(value)
                .setFormattedValue(String.format("%s €", String.format(Locale.FRANCE, "%.3f", value)));

            stocks.add(stock);
        });

        stocks.sort((o1, o2) -> (int) (o2.getValue() - o1.getValue()));

        return stocks;
    }
}
