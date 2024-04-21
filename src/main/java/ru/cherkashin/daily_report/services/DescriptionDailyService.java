package ru.cherkashin.daily_report.services;

import org.springframework.stereotype.Service;
import ru.cherkashin.daily_report.dto.InfoTableDTO;
import ru.cherkashin.daily_report.models.DescriptionDaily;
import ru.cherkashin.daily_report.repositorys.DescriptionDailyRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class DescriptionDailyService {

    private final DescriptionDailyRepository descriptionDailyRepository;
    private int min_size = 0;
    private int max_size = 0;

    public DescriptionDailyService(DescriptionDailyRepository descriptionDailyRepository){
        this.descriptionDailyRepository = descriptionDailyRepository;
        initMinAndMaxValues();
    }

    public Optional<DescriptionDaily> getRandomDescriptionOnId(){
        Random rand = new Random();
        int randomValue = rand.nextInt(min_size, max_size);
        return descriptionDailyRepository.getDescriptionDailyOnId(randomValue);
    }

    private void initMinAndMaxValues(){
        var optionalInfoTable = descriptionDailyRepository.getFirstAndLastId();
        if(optionalInfoTable.isPresent()){
            InfoTableDTO infoTableDTO = optionalInfoTable.get();
            max_size = infoTableDTO.getMax_size();
            min_size = infoTableDTO.getMin_size();
        }
    }

}
