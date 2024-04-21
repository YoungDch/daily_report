package ru.cherkashin.daily_report.repositorys;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.cherkashin.daily_report.models.DailyReport;
import ru.cherkashin.daily_report.models.DescriptionDaily;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class DailyReportRepository {

    private final JdbcTemplate jdbc;

    private final String QUERY_GET_REPORT_DAILY_DATE = "SELECT * FROM report_daily where date_report = ?";
    private final String UPDATE_INSERT_REPORT_DAILY = "INSERT INTO report_daily(date_report, description) VALUES(?, ?)";
    private final String UPDATE_DELTE_REPORT_DAILY_DATE = "DELETE FROM report_daily where date_report = ?";

    private final String LABEL_NAME_ID = "id";
    private final String LABEL_NAME_DATE_REPORT = "date_report";
    private final String LABEL_NAME_DESCRIPTION = "description";

    public DailyReportRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public boolean sendReportDaily(DescriptionDaily descriptionDaily, LocalDateTime dateTime){
        boolean result = true;
        try {
            jdbc.update(UPDATE_INSERT_REPORT_DAILY, dateTime, descriptionDaily.getDescription());
        }catch (RuntimeException ex){
            var logger = Logger.getLogger(getClass().getName());
            logger.info(ex.getMessage());
            result = false;
        }
        return result;
    }

    public List<DailyReport> getDailyReportOnDate(LocalDate date){
        return jdbc.query(QUERY_GET_REPORT_DAILY_DATE, (rs, rowNum) -> {
            DailyReport dailyReport = new DailyReport();
            dailyReport.setId(rs.getInt(LABEL_NAME_ID));
            dailyReport.setDate_report(LocalDate.parse(rs.getString(LABEL_NAME_DATE_REPORT)));
            dailyReport.setDescription(rs.getString(LABEL_NAME_DESCRIPTION));
            return dailyReport;
        },date);
    }

    public boolean deleteDailyReportOnDate(LocalDate date){
        boolean result = true;
        try{
            jdbc.update(UPDATE_DELTE_REPORT_DAILY_DATE, date);
        }catch (RuntimeException ex){
            var logger = Logger.getLogger(getClass().getName());
            logger.info(ex.getMessage());
            result = false;
        }
        return result;
    }

}
