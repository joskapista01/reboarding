package hu.vizespalack.api;

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

    public EntryDate(EntryDate date){
        this.dateString = date.dateString;
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
    }

    public EntryDate(String date){

        String[] s = date.split("-");

        this.dateString = date;
        this.year = Integer.parseInt(s[0]);
        this.month = Integer.parseInt(s[1]);
        this.day = Integer.parseInt(s[2]);
    }

    /**
     *
     * Returns string version of date (YYYY-MM-DD).
     *
     * @return value of month
     *
     */
    public String getDateString() {
        return this.dateString;
    }

    /**
     *
     * Returns year.
     *
     * @return value of year
     *
     */
    @Deprecated
    public Integer getYear() {
        return year;
    }

    /**
     *
     * Returns month.
     *
     * @return value of month
     *
     */
    @Deprecated
    public Integer getMonth() {
        return month;
    }

    /**
     *
     * Returns day.
     *
     * @return value of day
     *
     */
    @Deprecated
    public Integer getDay() {
        return day;
    }
}
