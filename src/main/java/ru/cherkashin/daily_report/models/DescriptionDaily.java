package ru.cherkashin.daily_report.models;


import java.util.Objects;


public class DescriptionDaily {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.isEmpty()){
            throw new RuntimeException("description is empty");
        }
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescriptionDaily that = (DescriptionDaily) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

}
