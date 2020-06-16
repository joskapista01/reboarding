package hu.vizespalack.api;

import org.threeten.bp.LocalDate;

import java.util.List;

/**
 * Handles the requests from the api.
 */

public class DataController {

    //TODO: capacity
    private static final Integer Capacity = 3;

    private final Integer currentCapacity;

    private final Backend backend;

    private LocalDate lastChange;

    public DataController(Backend backend) {
        this(Capacity, backend, LocalDate.now());
    }

    public DataController(Integer capacity, Backend backend, LocalDate localDate) {
        this.backend = backend;
        this.currentCapacity = capacity;
        //timezone
        this.lastChange = localDate;
    }

    /**
     *
     * The registerWorkerToDay method tries to register a worker to a given date.
     * If succesful, returns status (0 if can go in, else position in the waitinglist) else returns -1.
     *
     * @param worker    a worker object, containing the id of a worker
     * @param date      an EntryDate object , containing the date of the registration
     * @return          an integer representing the status of the request
     *
     * */

    public Integer registerWorkerToDay(Worker worker, EntryDate date) {
        notifyBackend();

        Integer position = backend.registerWorkerToDay(worker, date) - currentCapacity;

        if(position<=0) return 0;

        return position;

    }

    public Integer getPositionOnDay(EntryDate date, Worker worker) {
        return backend.getWorkerPosition(date, worker);
    }

    public Integer getCurrentPosition(Worker worker) {
        Integer position = backend.getWorkerPosition(new EntryDate(LocalDate.now()), worker);
        return position >= 0 ? position : -1;
    }

    /**
     *
     * Returns a list, containing WaitingListEntry objects with the workers id, dates and statuses of the requests, based on the workers id.
     *
     * @param worker a worker object, containing the id of a worker
     * @return       a list, based on the workers id
     *
     */
    public List<WaitingListEntry> getAllPosition(Worker worker) {

        return backend.getWorkerPositions(worker, currentCapacity);

    }

    /**
     *
     * Returns true, if worker is permitted to enter.
     * Else returns false.
     *
     * @param worker a worker object, containing the id of a worker
     * @return True/false (allowed / not allowed to enter)
     *
     * */
    public Boolean isPermittedToEnter(Worker worker) {

        return backend.addWorkerToOffice(worker, currentCapacity);

    }

    /**
     *
     * Based on the workers id returns true if exiting is successful,
     * else returns false.
     *
     * @param worker a worker object, containing the id of a worker
     * @return True/false (allowed / not allowed to enter)
     *
     */
    public Boolean exitWorkerFromOffice(Worker worker) {
        return backend.removeWorkerFromOffice(worker);
    }

    private void cleanWaitingList() {
        backend.clearWaitingList();
    }

    /**
     *
     * Clears the outdated waitinglistentrys every day.
     *
     */
    private void notifyBackend() {
        if (lastChange.getYear() == LocalDate.now().getYear() && lastChange.getDayOfYear() == LocalDate.now().getDayOfYear()) {
            cleanWaitingList();
        }
        lastChange = LocalDate.now();
    }

}
