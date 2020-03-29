package si.fri.mag.controllers.v1;

import com.google.gson.Gson;
import si.fri.mag.DTO.input.NewChunkInput;
import si.fri.mag.DTO.input.NewLinkInput;
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

    @POST
    @Path("newMediaChunk")
    public Response addNewMediaChunk(String body) {
        Gson gson = new Gson();
        NewChunkInput chunkInput;
        try {
            chunkInput = gson.fromJson(body, NewChunkInput.class);
        } catch (Exception e) {
            return  this.responseError(500, "failed to parse input data");
        }
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = chunksMetadataService.addNewMediaChunk(chunkInput);
        return this.responseOk("", linkMediaChunkDTOS);
    }

    @POST
    @Path("media-link")
    public Response linkChunkWithMedia(String body){
        Gson gson = new Gson();
        NewLinkInput linkInput;
        try {
            linkInput = gson.fromJson(body, NewLinkInput.class);
        } catch (Exception e) {
            return  this.responseError(500, "failed to parse input data");
        }

        boolean isMediaAndChunkLinked = chunksMetadataService.linkMediaAndChunk(linkInput);
        return this.responseOk("Media has been linked with chunk", isMediaAndChunkLinked);
    }

}
