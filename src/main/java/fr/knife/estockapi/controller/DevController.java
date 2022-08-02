package fr.knife.estockapi.controller;

import fr.knife.estockapi.service.DailyReportsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for tests. Only available with "dev" profile
 */
@RestController
@RequestMapping(path = "/api/dev", produces = MediaType.APPLICATION_JSON_VALUE)
public class DevController {
    /**
     * The service to manage daily reports
     */
    private final DailyReportsService dailyReportsService;

    /**
     * Create a new instance
     *
     * @param dailyReportsService The service to manage daily reports
     */
    public DevController(DailyReportsService dailyReportsService) {
        this.dailyReportsService = dailyReportsService;
    }

    /**
     * Send the daily reports email
     */
    @GetMapping("/daily-reports/stock/send-email")
    public void sendStockDailyReports() {
        this.dailyReportsService.sendStockDailyReports();
    }
}
