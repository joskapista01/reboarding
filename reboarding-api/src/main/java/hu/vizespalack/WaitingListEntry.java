package hu.vizespalack;

import org.threeten.bp.LocalDate;

public class WaitingListEntry {

    private final Worker worker;
    private final EntryDate date;
    private Integer listPosition;

    public WaitingListEntry(String workerId, LocalDate date, Integer listPosition) {
        this.worker = new Worker(workerId);
        this.date = new EntryDate(date);
        this.listPosition = listPosition;
    }

    public String getWorkerId() {
        return this.worker.getId();
    }

    public String getDateString() {
        return this.date.getDateString();
    }

    public void setListPosition(Integer position) { this.listPosition = position; }

    public Integer getListPosition() {
        return this.listPosition;
    }

}
