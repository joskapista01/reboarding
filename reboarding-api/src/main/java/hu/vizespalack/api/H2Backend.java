package hu.vizespalack.api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.threeten.bp.LocalDate;

import java.util.List;

/*
 *
 * an implementation of the Backend interface
 *
 * */
public class H2Backend implements Backend {

    private final JdbcTemplate jdbc;

    public H2Backend(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    /**
     *
     * Returns the position of a worker in the waitinglist for today based on the workers Id.
     * Returns -1 if worker is not on the list
     *
     * @param worker a worker object, containing the id of a worker
     * @return an integer, representing the workers position
     * */
    @Override
    public Integer getWorkerPosition(Worker worker) {

        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String todaysDateString = todaysDate.getDateString();

        String workerId = worker.getId();

        String sql = "SELECT COUNT(workerId) FROM waitinglist WHERE workerId = " + (char) 39 + workerId + (char) 39 + " AND entryDate = " + (char) 39 + todaysDateString + (char) 39 + ";";

        Integer result = jdbc.queryForObject(sql, Integer.class);

        if (result <= 0) return -1;

        sql = "SELECT listPosition FROM waitinglist WHERE workerId = " + (char) 39 + workerId + (char) 39 + " AND entryDate = " + (char) 39 + todaysDateString + (char) 39 + ";";
        result = jdbc.queryForObject(sql, Integer.class);

        return result;


    }
    /**
     *
     * Returns the position of a worker in the waitinglist for s given based on the workers Id and the date of that day.
     * Returns -1 if worker is not on the list.
     *
     * @param worker a worker object, containing the id of a worker
     * @param date an EntryDate object , containing the date of the registration
     * @return an integer, representing the workers position
     */
    @Override
    public Integer getWorkerPosition(EntryDate date, Worker worker) {

        String entryDateString = date.getDateString();

        String workerId = worker.getId();

        String sql = "SELECT COUNT(workerId) FROM waitinglist WHERE workerId " + (char) 39 + workerId + (char) 39 + " AND entryDate = " + (char) 39 + entryDateString + (char) 39 + ";";
        //a query that returns the position of a worker on the list on a given day (date)

        Integer result = jdbc.queryForObject(sql, Integer.class);

        if (result <= 0) return -1;

        sql = "SELECT listPosition FROM waitinglist WHERE workerId = " + (char) 39 + workerId + (char) 39 + " AND entryDate = " + (char) 39 + new EntryDate(LocalDate.now()) + (char) 39 + ";";
        result = jdbc.queryForObject(sql, Integer.class);

        return result;


    }
    /**
     *
     * Returns a list of WaitingListEntry objects,
     * containing the statuses of a workers active registration based on the workers id.
     *
     * @param worker a worker object, containing the id of a worker
     * @param capacity capacity of the office
     * @return:
     *              a list of WaitingListEntry objects with the following properties:
     *                  workerId: ID ofa worker
     *                  entryDate: date of the request
     *                  listPosition: position of the worker on the waitinglist on a given day
     *                       - 0, if the worker is allowed to go in straight away on the given day
     *                       - else the positon on the list (1 or bigger Integer)
     *
     * */
    @Override
    public List<WaitingListEntry> getWorkerPositions(Worker worker, Integer capacity) {

        String workerId = worker.getId();

        String sql = "SELECT workerId, entryDate, listPosition FROM waitinglist WHERE workerId = " + (char) 39 + workerId + (char) 39 + ";";
        //a query that returns all the active request's of a worker


        List<WaitingListEntry> result = jdbc.query(sql, new WaitingListEntryRowMapper());


        EntryDate todaysDate = new EntryDate(LocalDate.now());

        //calculating position:
        for(WaitingListEntry position : result)
        {
            Integer listPosition = position.getListPosition() - capacity;

            if(position.getDateString() == todaysDate.getDateString()) {
                listPosition += getNumberOfWorkersInOffice();
            }

            if(listPosition<0) listPosition = 0;

            position.setListPosition(listPosition);

        }

        return result;

    }

    @Override
    public List<WaitingListEntry> getWaitingList(EntryDate date) {

        String entryDateString = date.getDateString();

        String sql = "SELECT workerId, entryDate, listPosition FROM waitinglist WHERE entryDate = " + (char) 39 + entryDateString + (char) 39 + ";";
        //a query that returns all the active requests on a specific day


        List<WaitingListEntry> result = jdbc.query(sql, new WaitingListEntryRowMapper());

        return result;


        //returns the waitinglist on a given day (date)

    }
    /**
     *
     * When called, clears the outdated entrys.
     *
     */
    @Override
    public void clearWaitingList() {

        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String todaysDateString = todaysDate.getDateString();

        String sql = "DELETE FROM waitinglist WHERE entryDate < " + (char) 39 + todaysDateString + (char) 39 + ";";
        //a query that deletes the outdated entrys from the waitinglist

        jdbc.execute(sql);
        //executes the query
    }

    /**
     *
     * Returns the list of workers in the office.
     *
     * @return a list containing Worker objects
     *
     */
    @Override
    public List<Worker> getWorkersInOffice(EntryDate date) {
        String sql = "SELECT * FROM loggedinworkers;";
        //a query that returns all logged in workers in the office

        List<Worker> result = jdbc.query(sql, new WorkerRowMapper());

        return result;
        //returns a list of the workers in the office
    }
    /**
     *
     * Handles the process of a registration of a worker for a specific day.
     * Returns an integer, based on the success of the registration:
     *          -1: unsuccessful
     *          0: the register is allowed to go in on the given day
     *          else: position on the waitinglist on the given day
     *
     * @param worker a worker object, containing the id of a worker
     * @param date an EntryDate object , containing the date of the registration
     * @return An integer, representing the status of the request
     *
     *
     */
    @Override
    public Integer registerWorkerToDay(Worker worker, EntryDate date) {

        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String workerId = worker.getId();

        String todaysDateString = todaysDate.getDateString();

        String entryDateString = date.getDateString();

        String sql;

        Integer result;
        if (entryDateString.equals(todaysDateString)) {

            sql = "SELECT COUNT(workerId) FROM loggedinworkers WHERE workerId = " + (char) 39 + workerId + (char) 39 + ";";

            result = jdbc.queryForObject(sql, Integer.class);


            if (result > 0)
                return -1;

        }
        //if the request is for today, checks whether the worker is already in the office
        //if yes, denies request immediately (returns -1)

        sql = "SELECT COUNT(workerId) FROM waitinglist WHERE workerId = " + (char) 39 + workerId + (char) 39 + " AND entryDate = " + (char) 39 + entryDateString + (char) 39 + ";";
        //a query thate returns if the client is already in the waitinglist for a specific day

        result = jdbc.queryForObject(sql, Integer.class);

        //if the worker is eligible for registration
        if (result == 0) {
            sql = "SELECT COUNT(workerId) FROM waitinglist WHERE entryDate = " + (char) 39 + entryDateString + (char) 39 + ";";
            //a query that returns the number of workers on the waitinglist, to calculate the position of the client

            result = jdbc.queryForObject(sql, Integer.class) + 1;

            sql = "INSERT INTO waitinglist (workerId, entryDate, listPosition) VALUES (" + (char) 39 + workerId + (char) 39 + ", " + (char) 39 + entryDateString + (char) 39 + ", " + result + ");";
            //creatas an entry on the waitinglist with the workers id, the date and the workers position


            jdbc.execute(sql);

            if(date == todaysDate) result += getNumberOfWorkersInOffice();

            return result;
            //returns the position on the waitinglist
        }

        return -1;
    }

    /**
     *
     * Returns how many logged in workers are in the office right now.
     *
     * @return an Integer, representing the number of workers in the office
     *
     */
    @Override
    public Integer getNumberOfWorkersInOffice() {
        String sql = "SELECT COUNT(workerId) FROM loggedinworkers;";


        return jdbc.queryForObject(sql, Integer.class);
        //returns the number of workers in the office

    }

    /**
     * Decides whether the worker is permitted to enter the office right now.
     * If yes, removes the worker from the waitinglist, and adds him to the office and returns true.
     * Else returns false
     *
     * @param worker a worker object, containing the id of a worker
     * @param capacity capacity of the office
     * @return true/false (accepted/denied)
     */
    @Override
    public Boolean addWorkerToOffice(Worker worker, Integer capacity) {
        //returns true if the worker is permitted to enter, else returns false

        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String workerId = worker.getId();

        String todaysDateString = todaysDate.getDateString();

        Integer currentPosition = getWorkerPosition(worker);
        //gets the worker position on todays waitinglist

        String sql;
        if (currentPosition != -1) {
            //continues if the worker is on todays list
            Integer freeSpacesInOffice = capacity - getNumberOfWorkersInOffice();
            if (currentPosition <= freeSpacesInOffice) { //checks if to workers is eligible to enter the office
                sql = "DELETE FROM waitinglist WHERE workerId = " + (char) 39 + workerId + (char) 39 + " AND entryDate = " + (char) 39 + todaysDateString + (char) 39 + ";";
                //removes worker from wl
                jdbc.execute(sql);

                sql = "UPDATE waitinglist SET listPosition = listPosition + 1 WHERE entryDate = " + (char) 39 + todaysDateString + (char) 39 + ";";
                //updates the other workers position

                jdbc.execute(sql);

                sql = "INSERT INTO loggedinworkers (workerId) VALUES (" + (char) 39 + workerId + (char) 39 + ");";
                //puts worker in the office

                jdbc.execute(sql);

                return true;
            }
            return false;
        }
        return false;
    }


    /**
     * Removes worker from the office, when exiting. Returns tru if successful, else returns false.
     *
     * @param worker a worker object, containing the id of a worker
     * @return true/false (successful/failed)
     */
    @Override
    public Boolean removeWorkerFromOffice(Worker worker) {

        String workerId = worker.getId();

        String sql = "SELECT COUNT(workerId) FROM loggedinworkers WHERE workerId = " + (char) 39 + workerId + (char) 39 + ";";
        //checks if the worker is in the office
        Integer result = jdbc.queryForObject(sql, Integer.class);

        if (result > 0) {
            sql = "DELETE FROM loggedinworkers WHERE workerId = " + (char) 39 + workerId + (char) 39 + ";";
            //removes worker from the office
            jdbc.execute(sql);

            return true;
        }
        return false;
    }

}
