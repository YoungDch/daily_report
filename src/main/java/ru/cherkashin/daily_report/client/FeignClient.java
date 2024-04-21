package ru.cherkashin.daily_report.client;

import org.springframework.web.bind.annotation.PostMapping;
import ru.cherkashin.daily_report.models.DailyReport;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "feignClient", url = "http://localhost:8081")
public interface FeignClient {

    @PostMapping(value = "/daily_report/checking")
    boolean postDailyReport(List<DailyReport> dailyReport);

}
