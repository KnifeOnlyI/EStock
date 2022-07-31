package fr.knife.estockapi.schedule;


import fr.knife.estockapi.service.DailyReportsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * All schudules for daily reports
 */
@Component
public class DailyReportSchedules {
    /**
     * The service to manage daily reports
     */
    private final DailyReportsService dailyReportsService;

    /**
     * Create a new instance
     *
     * @param dailyReportsService The service to manage daily reports
     */
    public DailyReportSchedules(DailyReportsService dailyReportsService) {
        this.dailyReportsService = dailyReportsService;
    }

    /**
     * The schedule for stock daily reports
     */
    @Scheduled(cron = "${app.stock.daily-report.cron}")
    public void sendStockDailyReports() {
        this.dailyReportsService.sendStockDailyReports();
    }
}
