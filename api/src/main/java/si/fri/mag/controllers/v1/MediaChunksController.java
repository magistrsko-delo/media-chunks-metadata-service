package si.fri.mag.controllers.v1;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.controllers.MainController;
import si.fri.mag.services.MediaChunkService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/v1/media")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MediaChunksController extends MainController {

    @Inject
    private MediaChunkService mediaChunkService;

    @GET
    @Path("{mediaId}/chunks")
    public Response getAllMediaChunks(@PathParam("mediaId") Integer mediaId) {
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = mediaChunkService.getMediaChunks(mediaId);
        return this.responseOk("", linkMediaChunkDTOS);
    }

    @GET
    @Path("{mediaId}/resolution/{resolution}/chunks")
    public Response getAllMediaChunksResolution(@PathParam("mediaId") Integer mediaId, @PathParam("resolution") String resolution) {
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = mediaChunkService.getMediaChunksResolution(mediaId, resolution);
        return this.responseOk("", linkMediaChunkDTOS);
    }

    @GET
    @Path("resolution")
    public Response getAvailableResolutions() {
        List<String> resolutions = mediaChunkService.getAvailableResolutions();
        return this.responseOk("", resolutions);
    }

}
