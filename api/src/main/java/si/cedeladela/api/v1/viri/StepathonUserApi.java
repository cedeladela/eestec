
package si.cedeladela.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
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
import java.util.List;

@Path("stepathon-user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class StepathonUserApi {


    @Inject
    private StepathonUserManager stepathonUserManager;

    @Tag(name = "StepathonUser")
    @Operation(description = "Vrne uporabnika glede na id.", summary = "Stepathon user glede na id.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "StepathonUser.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StepathonUserDto.class))),
            @APIResponse(responseCode = "404",
                    description = "StepathonUser ne obstaja.")
    })
    @GET
    @Path("byId/{id}")
    public Response getStepathonUser(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(StepathonUserMapper.stepathonUserToStepathonUserDto(stepathonUserManager.getById(id))).build();
    }
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

    @Tag(name = "StepathonUser")
    @Operation(description = "Vrne seznam vseh userjev.", summary = "Seznam vseh userjev z izracunanim scorom.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Seznam userjev.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StepathonUserDto.class)))
    })
    @GET
    public Response getAllStepathonUsers() {

        return Response.ok(StepathonUserMapper.stepathonUserToStepathonUserDto(stepathonUserManager.getAll())).build();
    }

    @Tag(name = "StepathonUser")
    @Operation(description = "Ustvari novega stepathonUserja", summary = "Ustvari stepathonUserja.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Nov stepathonUser.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StepathonUserDto.class))),
            @APIResponse(responseCode = "400",
                    description = "Napaka pri ustvarjanju stepathonUserja.")
    })
    @POST
    public Response createStepathonUser(StepathonUserDto stepathonUserDto) {
        return Response.status(Response.Status.CREATED).entity(StepathonUserMapper.stepathonUserToStepathonUserDto(stepathonUserManager.create(StepathonUserMapper.stepathonUserDtoToStepathonUser(stepathonUserDto)))).build();
    }

    @Tag(name = "StepathonUser")
    @Path("steps")
    @Operation(description = "Posodobi korake", summary = "Posodobi korake\".")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Posodobi korake\".",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StepathonUserDto.class))),
            @APIResponse(responseCode = "400",
                    description = "Napaka pri Posodobi korake\".")
    })
    @POST
    public Response setSteps(StepathonUserDto stepathonUserDto) {
        return Response.status(Response.Status.CREATED).entity(StepathonUserMapper.stepathonUserToStepathonUserDto(stepathonUserManager.setSteps(StepathonUserMapper.stepathonUserDtoToStepathonUser(stepathonUserDto)))).build();
    }

}