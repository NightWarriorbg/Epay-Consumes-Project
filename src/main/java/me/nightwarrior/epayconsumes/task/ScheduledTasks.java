package me.nightwarrior.epayconsumes.task;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    MerchantService merchantService;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(cron = "0 0 12 * * *")
    //@Scheduled(fixedDelay = 1000)
    public void updateMerchantsDatabaseTask() throws SQLException {
        log.info("Updating Database Task :: Execution Time - {}", DATE_TIME_FORMATTER.format(LocalDateTime.now()));
        merchantService.populateMerchants();
    }
}
