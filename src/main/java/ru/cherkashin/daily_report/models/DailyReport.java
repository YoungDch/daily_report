package ru.cherkashin.daily_report.models;

import java.time.LocalDate;
import java.util.Objects;

public class DailyReport {

    private int id;
    private LocalDate date_report;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate_report() {
        return date_report;
    }

    public void setDate_report(LocalDate date_report) {
        this.date_report = date_report;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReport that = (DailyReport) o;
        return id == that.id && Objects.equals(date_report, that.date_report) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date_report, description);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", date_report=" + date_report +
                ", description='" + description + '\'' +
                '}';
    }
}
