package hu.vizespalack.spring.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.vizespalack.api.DataController;
import hu.vizespalack.api.EntryDate;
import hu.vizespalack.api.H2Backend;
import hu.vizespalack.api.Worker;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.threeten.bp.LocalDate;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-15T17:15:26.129Z")

@Controller
public class ExitApiController implements ExitApi {

    private static final Logger log = LoggerFactory.getLogger(ExitApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @org.springframework.beans.factory.annotation.Autowired
    public ExitApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> exiting(@ApiParam(value = "ID of the user who the request came from", required = true) @PathVariable("workerId") String workerId) {

        Worker worker = new Worker(workerId);
        EntryDate entryDate = new EntryDate(LocalDate.now());

        DataController controller = new DataController(new H2Backend(jdbc));

        return ResponseEntity.ok(controller.exitWorkerFromOffice(worker));
    }

}
