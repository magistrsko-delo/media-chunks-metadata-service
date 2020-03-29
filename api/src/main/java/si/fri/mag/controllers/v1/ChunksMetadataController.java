package si.fri.mag.controllers.v1;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.controllers.MainController;
import si.fri.mag.services.ChunksMetadataService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/v1/chunk/metadata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChunksMetadataController extends MainController {

    @Inject
    private ChunksMetadataService chunksMetadataService;

    @GET
    @Path("all")
    public Response getAllMediaChunksMetadata() {
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = chunksMetadataService.getAllChunks();
        return this.responseOk("", linkMediaChunkDTOS);
    }

    @GET
    @Path("{chunkId}")
    public Response getChunkMetadata(@PathParam("chunkId") Integer chunkId) {
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = chunksMetadataService.getChunk(chunkId);
        return this.responseOk("", linkMediaChunkDTOS);
    }

}
