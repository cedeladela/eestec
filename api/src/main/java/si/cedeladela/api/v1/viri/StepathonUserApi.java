
package si.cedeladela.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.cedeladela.dtos.StepathonUserDto;
import si.cedeladela.managers.StepathonUserManager;
import si.cedeladela.mappers.StepathonUserMapper;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("stepathon-user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class StepathonUserApi {


    @Inject
    private StepathonUserManager stepathonUserManager;


    @Tag(name = "StepathonUser")
    @Operation(description = "Vrne uporabnika glede na username.", summary = "Stepathon user glede na username.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "StepathonUser.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StepathonUserDto.class))),
            @APIResponse(responseCode = "404",
                    description = "StepathonUser ne obstaja.")
    })
    @GET
    @Path("{username}")
    public Response getStepathonUser(@PathParam("username") String username) {
        return Response.status(Response.Status.OK).entity(StepathonUserMapper.stepathonUserToStepathonUserDto(stepathonUserManager.getByUsername(username))).build();
    }

}