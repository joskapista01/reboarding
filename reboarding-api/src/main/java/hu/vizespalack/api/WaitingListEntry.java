package hu.vizespalack.api;

//a class that can contain the parameters of an entry on a waitinglist
public class WaitingListEntry {

    private final Worker worker;
    private final EntryDate date;
    private Integer listPosition;

    public WaitingListEntry(Worker worker, EntryDate date, Integer listPosition) {
        this.worker = worker;
        this.date = new EntryDate(date);
        this.listPosition = listPosition;
    }
    /**
     *
     * Returns workerId.
     *
     * @return value of workerId
     *
     */
    public String getWorkerId() {
        return this.worker.getId();
    }

    /**
     *
     * Returns date converted to string.
     *
     * @return string value of date
     *
     */
    public String getDateString() {
        return this.date.getDateString();
    }

    /**
     *
     * Sets the listposition to the given parameter.
     *
     * @param position new value of position
     * @return value of workerId
     *
     */
    public void setListPosition(Integer position) { this.listPosition = position; }

    /**
     *
     * Returns listposition.
     *
     * @return value of listposition
     *
     */
    public Integer getListPosition() {
        return this.listPosition;
    }

}
