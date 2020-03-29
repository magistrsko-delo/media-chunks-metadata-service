package si.fri.mag.controllers.v1;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.controllers.MainController;
import si.fri.mag.services.MediaChunksMetadataService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/v1/chunks/metadata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MediaChunksMetadataController extends MainController {

    @Inject
    private MediaChunksMetadataService mediaChunksMetadataService;

    @GET
    @Path("all")
    public Response getAllMediaMetadata() {
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = mediaChunksMetadataService.getAllMediaChunks();
        return this.responseOk("", linkMediaChunkDTOS);
    }

}
