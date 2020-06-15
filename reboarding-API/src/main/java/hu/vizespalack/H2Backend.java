package hu.vizespalack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.threeten.bp.LocalDate;

import java.util.List;

public class H2Backend implements Backend {


    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Integer getWorkerPosition(Worker worker) {

        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String todaysDateString = todaysDate.getDateString();

        String workerId = worker.getId();


        String sql = "SELECT listPosition FROM waitinglist WHERE workerId = " + (char) 34 + workerId + (char) 34 + " AND entryDate = " + (char) 34 + todaysDateString + (char) 34 + ";";

        Integer result = jdbc.queryForObject(sql, Integer.class);

        if (result == null) return -1;

        return result;
    }

    @Override
    public Integer getWorkerPosition(EntryDate date, Worker worker) {

        String entryDateString = date.getDateString();

        String workerId = worker.getId();

        String sql = "SELECT listPosition FROM waitinglist WHERE workerId = " + (char) 34 + workerId + (char) 34 + " AND entryDate = " + (char) 34 + entryDateString + (char) 34 + ";";

        Integer result = jdbc.queryForObject(sql, Integer.class);

        if (result == null) return -1;

        return result;
    }

    @Override
    public List<WaitingListEntry> getWorkerPositions(Worker worker) {

        String workerId = worker.getId();

        String sql = "SELECT listPosition FROM waitinglist WHERE workerId = " + (char) 34 + workerId + (char) 34 + ";";

        List<WaitingListEntry> result = jdbc.query(sql, new WaitingListEntryRowMapper());


        return result;
    }

    @Override
    public List<WaitingListEntry> getWaitingList(EntryDate date) {

        String entryDateString = date.getDateString();

        String sql = "SELECT workerId, entryDate, listPosition FROM waitinglist WHERE entryDate = " + (char) 34 + entryDateString + (char) 34 + ";";

        List<WaitingListEntry> result = jdbc.query(sql, new WaitingListEntryRowMapper());

        return result;
    }

    @Override
    public void clearWaitingList() {

        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String todaysDateString = todaysDate.getDateString();

        String sql = "DELETE FROM waitinglist WHERE entryDate < " + (char) 34 + todaysDateString + (char) 34 + ";";

        jdbc.execute(sql);
    }

    @Override
    public List<Worker> getWorkersInOffice(EntryDate date) {
        String sql = "SELECT * FROM loggedinworkers";


        List<Worker> result = jdbc.query(sql, new WorkerRowMapper());

        return result;
    }

    @Override
    public Integer registerWorkerToDay(Worker worker, EntryDate date) {

        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String workerId = worker.getId();

        String todaysDateString = todaysDate.getDateString();

        String entryDateString = date.getDateString();

        String sql;

        Integer result = -1;
        if (entryDateString == todaysDateString) {

            sql = "SELECT COUNT(workerId) FROM loggedinworkers WHERE workerId = " + (char) 34 + workerId + (char) 34 + ";";

            result = jdbc.queryForObject(sql, Integer.class);


            if (result > 0)
                return -1;

        }

        sql = "SELECT COUNT(workerId) FROM waitinglist WHERE workerId = " + (char) 34 + workerId + (char) 34 + " AND entryDate = " + (char) 34 + entryDateString + (char) 34 + ";";

        result = jdbc.queryForObject(sql, Integer.class);

        if (result == 0) {
            sql = "SELECT COUNT(workerId) FROM waitinglist WHERE entryDate = " + (char) 34 + entryDateString + (char) 34 + ";";

            result = jdbc.queryForObject(sql, Integer.class) + 1;

            sql = "INSERT INTO waitinglist (workerId, entryDate, listPosition) VALUES (" + (char) 34 + workerId + (char) 34 + ", " + (char) 34 + entryDateString + (char) 34 + ", " + (char) 34 + result + (char) 34 + ");";

            jdbc.execute(sql);

            return result;
        }

        return -1;
    }

    @Override
    public Integer getNumberOfWorkersInOffice() {
        String sql = "SELECT COUNT(workerId) FROM loggedinworkers;";

        Integer result = jdbc.queryForObject(sql, Integer.class);


        return result;

    }

    @Override
    public Boolean addWorkerToOffice(Worker worker, Integer capacity) {


        EntryDate todaysDate = new EntryDate(LocalDate.now());

        String workerId = worker.getId();

        String todaysDateString = todaysDate.getDateString();

        Integer currentPosition = getWorkerPosition(worker);

        String sql;
        if (currentPosition != -1) {
            Integer freeSpacesInOffice = capacity - getNumberOfWorkersInOffice();
            if (currentPosition <= freeSpacesInOffice) {
                sql = "DELETE FROM waitinglist WHERE workerId = " + (char) 34 + workerId + (char) 34 + " AND entryDate = " + todaysDateString + ";";

                jdbc.execute(sql);

                sql = "INSERT INTO loggedinworkers (workerId) VALUES (" + (char) 34 + workerId + (char) 34 + ");";

                jdbc.execute(sql);

                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Boolean removeWorkerFromOffice(Worker worker) {

        String workerId = worker.getId();

        String sql = "SELECT COUNT(workerId) FROM loggedinworkers WHERE workerId = " + (char) 34 + workerId + (char) 34 + ";";

        Integer result = jdbc.queryForObject(sql, Integer.class);

        if (result > 0) {
            sql = "DELETE FROM loggedinworkers WHERE workerId = " + workerId + "";

            jdbc.execute(sql);

            return true;
        }
        return false;
    }
}
