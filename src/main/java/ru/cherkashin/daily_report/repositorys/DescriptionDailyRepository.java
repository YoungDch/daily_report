package ru.cherkashin.daily_report.repositorys;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.cherkashin.daily_report.dto.InfoTableDTO;
import ru.cherkashin.daily_report.models.DescriptionDaily;

import java.util.Optional;

@Repository
public class DescriptionDailyRepository {

    private final String QUERY_GET_DESCRIPTION_DAILY_ID = "SELECT * from description_daily WHERE id = %s";
    private final String QUERY_GET_FISR_AND_LAST_ID = "SELECT min(id), max(id) from description_daily";
    private final String LABEL_NAME_DESCRIPTION = "Description";
    private final JdbcTemplate jdbc;

    public DescriptionDailyRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public Optional<DescriptionDaily> getDescriptionDailyOnId(int id){
        Optional<DescriptionDaily> result = Optional.empty();
        String sqlQuary = String.format(QUERY_GET_DESCRIPTION_DAILY_ID, id);
        var descriptionList = jdbc.query(sqlQuary, (rs, rowNum) -> {
            DescriptionDaily descriptionDaily = new DescriptionDaily();
            descriptionDaily.setDescription(rs.getString(LABEL_NAME_DESCRIPTION));
            return descriptionDaily;
        });
        if(!descriptionList.isEmpty()){
            result = Optional.of(descriptionList.get(0));
        }
        return result;
    }

    public Optional<InfoTableDTO> getFirstAndLastId(){
        Optional<InfoTableDTO> optionalInfoTable = Optional.empty();
        var result = jdbc.query(QUERY_GET_FISR_AND_LAST_ID, (rs, rowNum) -> {
            InfoTableDTO infoTableDTO = new InfoTableDTO();
            infoTableDTO.setMax_size(rs.getInt("max"));
            infoTableDTO.setMin_size(rs.getInt("min"));
            return infoTableDTO;
        });
        if(!result.isEmpty()){
            optionalInfoTable = Optional.of(result.get(0));
        }
        return optionalInfoTable;
    }
}
