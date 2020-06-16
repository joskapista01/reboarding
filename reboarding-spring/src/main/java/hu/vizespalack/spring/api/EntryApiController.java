package hu.vizespalack.spring.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.vizespalack.api.H2Backend;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.threeten.bp.LocalDate;
import hu.vizespalack.api.Worker;
import hu.vizespalack.api.EntryDate;
import hu.vizespalack.api.DataController;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-15T17:15:26.129Z")

@Controller
public class EntryApiController implements EntryApi {

    private static final Logger log = LoggerFactory.getLogger(EntryApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @org.springframework.beans.factory.annotation.Autowired
    public EntryApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> returnPermission(@ApiParam(value = "ID of the user who the request came from", required = true) @PathVariable("workerId") String workerId) {

        Worker worker = new Worker(workerId);
        EntryDate entryDate = new EntryDate(LocalDate.now());

        DataController controller = new DataController(new H2Backend(jdbc));

        return ResponseEntity.ok(controller.isPermittedToEnter(worker));
    }

}
