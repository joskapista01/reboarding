package hu.vizespalack;

import org.threeten.bp.LocalDate;

import java.util.List;

public class DataController {

    //TODO: capacity
    private static final Integer Capacity = 50;

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

    /*/register/{workerId}(/{date}) //TODO: át kell adni POST-tal/URL-ben a dátumot
     *
     * tries to register a worker for the requested date
     *
     * if succesful, returns status (0 if can go in, else number in the waitinglist)
     *
     * else returns -1
     *
     * */

    public Integer registerWorkerToDay(Worker worker, EntryDate date) {
        notifyBackend();

        return backend.registerWorkerToDay(worker, date);

        //if only one registration can be on a singe day
        /*
        Integer position = backend.getWorkerPositionById(date, worker);
        if (position < 0){
            return backend.registerWorkerToDay(date, worker);
        }
        return -1;
         */

    }

    /*status/{workerId}
     *
     * returns a list, containing active requests
     *
     * the list contains pairs of dates and statuses of requests
     *  */

    public Integer getPositionOnDay(EntryDate date, Worker worker) {
        return backend.getWorkerPosition(date, worker);
    }

    public Integer getCurrentPosition(Worker worker) {
        Integer position = backend.getWorkerPosition(new EntryDate(LocalDate.now()), worker);
        return position >= 0 ? position : -1;
    }

    public List<WaitingListEntry> getAllPosition(Worker worker) {
        return backend.getWorkerPositions(worker);
    }

    /*entry/{workerId}
     *
     * returns true, if worker is permitted to enter, removes him/her from wl, puts him in the office
     * else returns false
     *
     * */

    public Boolean isPermittedToEnter(Worker worker) {

        Integer inOffice = backend.getNumberOfWorkersInOffice();

        //counts from one
        return currentCapacity - inOffice >= backend.getWorkerPosition(worker);

    }

    /*/exit/{workerId}
     *
     * returns true if exiting is successful
     * else returns false
     *
     * */

    public Boolean exitWorkerFromOffice(Worker worker) {
        return backend.removeWorkerFromOffice(worker);
    }

    private void cleanWaitingList() {
        backend.clearWaitingList();
    }

    private void notifyBackend() {
        if (lastChange.getYear() == LocalDate.now().getYear() && lastChange.getDayOfYear() == LocalDate.now().getDayOfYear()) {
            cleanWaitingList();
        }
        lastChange = LocalDate.now();
    }

}
