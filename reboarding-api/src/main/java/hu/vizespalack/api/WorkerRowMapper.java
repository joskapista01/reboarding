package hu.vizespalack;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class WorkerRowMapper implements RowMapper<Worker> {
    @Override
    public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Worker(rs.getString("workerId"));
    }
}