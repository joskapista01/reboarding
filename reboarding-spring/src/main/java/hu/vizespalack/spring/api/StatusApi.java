/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package hu.vizespalack.spring.api;

import hu.vizespalack.api.WaitingListEntry;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-15T17:15:26.129Z")

@Api(value = "status", description = "the status API")
@RequestMapping(value = "/v2")
public interface StatusApi {

    @ApiOperation(value = "returns the status of your  actual request", nickname = "returnStatus", notes = "", response = List.class, tags = {"status",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Succesful", response = Integer.class),
        @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = "/status/{workerId}",
        produces = {"application/json"},
        method = RequestMethod.GET)
    ResponseEntity<List<WaitingListEntry>> returnStatus(@ApiParam(value = "ID of the user who the request came from", required = true) @PathVariable("workerId") String workerId);

}
