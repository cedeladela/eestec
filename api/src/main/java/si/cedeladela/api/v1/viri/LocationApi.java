
package si.cedeladela.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.cedeladela.dtos.StepathonUserDto;
import si.cedeladela.entitete.Location;
import si.cedeladela.managers.LocationManager;
import si.cedeladela.managers.StepathonUserManager;
import si.cedeladela.mappers.LocationMapper;
import si.cedeladela.mappers.StepathonUserMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("location")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class LocationApi {


    @Inject
    private LocationManager locationManager;


    @Tag(name = "Location")
    @Operation(description = "Vrne seznam lokacij.", summary = "Seznam lokacij.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Location list.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Location.class))),
            @APIResponse(responseCode = "404",
                    description = "StepathonUser ne obstaja.")
    })
    @GET
    public Response getAll() {
        return Response.ok(LocationMapper.locationToLocationDto(locationManager.getAll())).build();
    }

}