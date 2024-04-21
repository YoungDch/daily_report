package ru.cherkashin.daily_report.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.cherkashin.daily_report.client.FeignClient;
import ru.cherkashin.daily_report.models.DescriptionDaily;

import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SchedulService {

    private final DescriptionDailyService dailyService;
    private final DailyReportService dailyReportService;
    private final FeignClient feignClient;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public SchedulService(DescriptionDailyService dailyService,
                          DailyReportService dailyReportService,
                          FeignClient feignClient){
        this.dailyService = dailyService;
        this.dailyReportService = dailyReportService;
        this.feignClient = feignClient;
    }

    @Async
    @Scheduled(fixedDelay = 10000)
    public void sendDescriptionDaily(){
        Optional<DescriptionDaily> optionalDescriptionDaily= dailyService.getRandomDescriptionOnId();
        if(optionalDescriptionDaily.isEmpty()){
            return;
        }
        DescriptionDaily descriptionDaily = optionalDescriptionDaily.get();
        boolean result = dailyReportService.sendReportDaily(descriptionDaily);
        if(!result){
            logger.log(Level.WARNING, "Report don't send");
        }
    }

    @Async
    @Scheduled(fixedDelay = 30000)
    public void sendReportOnFeignClient(){
        LocalDate dateNow = LocalDate.now();
        var dailyReportList = dailyReportService.getDailyReportOnDate(dateNow);
        if(dailyReportList.isEmpty()){
            return;
        }

        boolean isCorrect = false;
        try {
            isCorrect = feignClient.postDailyReport(dailyReportList);
        }catch (RuntimeException ex){
            logger.log(Level.WARNING, "Second service not active");
            return;
        }

        if(!isCorrect) {
            System.out.println("Report is not correctly - " + dailyReportList);
            return;
        }

        boolean result = dailyReportService.deleteReportDailyOnDate(dateNow);
        if(!result){
            logger.log(Level.WARNING, "Report don't delete");
            return;
        }

        System.out.println("Report is correctly - " + dailyReportList);
    }
}
