
package si.cedeladela.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.cedeladela.dtos.LocationDto;
import si.cedeladela.entitete.Location;
import si.cedeladela.managers.LocationManager;
import si.cedeladela.mappers.LocationMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("locationActivation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class ActivateLocationApi {


    @Inject
    private LocationManager locationManager;


    @Tag(name = "ActivateLocation")
    @Operation(description = "Aktiviraj lokacijo", summary = "Aktiviraaj lokacijo za izziv.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Aktivacija lokacije.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationDto.class))),
            @APIResponse(responseCode = "400",
                    description = "Napaka pri aktiviranju lokacije.")
    })
    @GET
    @Path("{id}")
    public Response activateLocation(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(LocationMapper.locationToLocationDto(locationManager.activateLocation(id))).build();
    }

    @Tag(name = "Challenge")
    @Operation(description = "Iziv lokacijo", summary = "Challenge location.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Challenge lokacije.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationDto.class))),
            @APIResponse(responseCode = "400",
                    description = "Napaka pri get challenge.")
    })
    @GET
    public Response getChallenge() {
        return Response.status(Response.Status.OK).entity(LocationMapper.locationToLocationDto(locationManager.getChallenge())).build();
    }
}