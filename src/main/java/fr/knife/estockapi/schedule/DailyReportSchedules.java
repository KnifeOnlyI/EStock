package fr.knife.estockapi.schedule;


import fr.knife.estockapi.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * All schudules for daily reports
 */
@Component
public class DailyReportSchedules {
    /**
     * The service to manage emails
     */
    private final EmailService emailService;

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
     * @param emailService The service to manage emails
     */
    public DailyReportSchedules(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * The schedule for stock daily reports
     */
    @Scheduled(cron = "${app.stock.daily-report.cron}")
    public void sendStockDailyReports() {
        // TODO: Send a real daily report
        this.emailService.send(this.receiver, this.stockDailyReportSubject, "Hello World !");
    }
}
