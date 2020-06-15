package hu.vizespalack;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

public class EntryDate {

    private final String dateString;
    private final Integer year, month, day;

    public EntryDate(LocalDate date) {
        this.dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
    }

    public String getDateString() {
        return this.dateString;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }
}
