package ru.cherkashin.daily_report.services;

import org.springframework.stereotype.Service;
import ru.cherkashin.daily_report.models.DailyReport;
import ru.cherkashin.daily_report.models.DescriptionDaily;
import ru.cherkashin.daily_report.repositorys.DailyReportRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DailyReportService {

    private final DailyReportRepository dailyReportRepository;

    public DailyReportService(DailyReportRepository dailyReportRepository) {
        this.dailyReportRepository = dailyReportRepository;
    }

    public boolean sendReportDaily(DescriptionDaily descriptionDaily){
        return dailyReportRepository.sendReportDaily(descriptionDaily, LocalDateTime.now());
    }

    public List<DailyReport> getDailyReportOnDate(LocalDate date){
        return dailyReportRepository.getDailyReportOnDate(date);
    }

    public boolean deleteReportDailyOnDate(LocalDate date){
        return dailyReportRepository.deleteDailyReportOnDate(date);
    }
}
