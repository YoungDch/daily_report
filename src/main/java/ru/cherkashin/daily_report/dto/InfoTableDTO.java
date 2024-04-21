package ru.cherkashin.daily_report.dto;

import java.util.Objects;

public class InfoTableDTO {

    private int min_size;
    private int max_size;

    public int getMin_size() {
        return min_size;
    }

    public void setMin_size(int min_size) {
        this.min_size = min_size;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }
}

