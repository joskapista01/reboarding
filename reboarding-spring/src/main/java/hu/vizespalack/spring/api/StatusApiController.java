package hu.vizespalack.spring.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.vizespalack.api.*;
import hu.vizespalack.spring.Swagger2SpringBoot;
import io.swagger.annotations.ApiParam;
import io.swagger.models.Swagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.threeten.bp.LocalDate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-15T17:15:26.129Z")

@Controller
public class StatusApiController implements StatusApi {

    private static final Logger log = LoggerFactory.getLogger(StatusApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @org.springframework.beans.factory.annotation.Autowired
    public StatusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<WaitingListEntry>> returnStatus(@ApiParam(value = "ID of the user who the request came from", required = true) @PathVariable("workerId") String workerId) {

        Worker worker = new Worker(workerId);
        EntryDate entryDate = new EntryDate(LocalDate.now());

        DataController controller = new DataController(Swagger2SpringBoot.getCapacity(), new H2Backend(jdbc));

        return ResponseEntity.ok(controller.getAllPosition(worker));

        /*String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            List<WaitingListEntry> list = controller.getAllPosition(worker);

            return new ResponseEntity<List<WaitingListEntry>>(list, HttpStatus.ACCEPTED);
        }*/

    }

}
