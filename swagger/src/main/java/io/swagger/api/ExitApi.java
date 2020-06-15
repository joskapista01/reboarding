/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-15T17:15:26.129Z")

@Api(value = "exit", description = "the exit API")
@RequestMapping(value = "/v2")
public interface ExitApi {

    @ApiOperation(value = "logs you out", nickname = "exiting", notes = "", tags = {"exit",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Succesful"),
        @ApiResponse(code = 405, message = "Method not allowed")})
    @RequestMapping(value = "/exit/{workerId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> exiting(@ApiParam(value = "ID of the user who the request came from", required = true) @PathVariable("workerId") String workerId);

}