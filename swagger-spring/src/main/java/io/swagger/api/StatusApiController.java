package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.vizespalack.*;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @org.springframework.beans.factory.annotation.Autowired
    public StatusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<WaitingListEntry>> returnStatus(@ApiParam(value = "ID of the user who the request came from", required = true) @PathVariable("workerId") String workerId) {

        Worker worker = new Worker(workerId);
        EntryDate entryDate = new EntryDate(LocalDate.now());

        DataController controller = new DataController(new H2Backend());

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            List<WaitingListEntry> list = controller.getAllPosition(worker);

            return new ResponseEntity<List<WaitingListEntry>>(list, HttpStatus.ACCEPTED);
        }

        return null;
    }

}
