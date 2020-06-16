package hu.vizespalack.api;

import org.springframework.jdbc.core.RowMapper;
import org.threeten.bp.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;

//handles the rowmapping of a queried WaitingListEntry
public class WaitingListEntryRowMapper implements RowMapper<WaitingListEntry> {
    @Override
    public WaitingListEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        String workerId = rs.getString("workerId");

        LocalDate entryDate = LocalDate.parse(rs.getDate("entryDate").toString());

        Integer listPosition = rs.getInt("listPosition");

        return new WaitingListEntry(new Worker(workerId), new EntryDate(entryDate), listPosition);
    }

}
