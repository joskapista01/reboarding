package hu.vizespalack;

import org.springframework.jdbc.core.RowMapper;
import org.threeten.bp.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;


public class WaitingListEntryRowMapper implements RowMapper<WaitingListEntry> {
    @Override
    public WaitingListEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        String workerId = rs.getString("workerId");

        LocalDate entryDate = LocalDate.parse(rs.getDate("entryDate").toString());

        Integer listPosition = rs.getInt("listPosition");

        WaitingListEntry wle = new WaitingListEntry(workerId, entryDate, listPosition);
        return wle;
    }

}
