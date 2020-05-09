package grpc;

import com.google.protobuf.Empty;
import com.kumuluz.ee.grpc.annotations.GrpcService;
import io.grpc.stub.StreamObserver;
import si.fri.mag.DTO.input.NewChunkInput;
import si.fri.mag.DTO.input.NewLinkInput;
import si.fri.mag.DTO.responses.ChunkDTO;
import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.services.ChunksMetadataService;
import si.fri.mag.services.MediaChunkService;

import javax.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class MediaChunkServiceMetadataServiceImpl extends MediaMetadataGrpc.MediaMetadataImplBase {
    ChunksMetadataService chunksMetadataService;
    MediaChunkService mediaChunkService;

    @Override
    public void newMediaChunk(MediachunksmetadataService.NewMediaChunkRequest request, StreamObserver<MediachunksmetadataService.MediaChunkInfoResponseRepeated> responseObserver) {
        NewChunkInput chunkInput = new NewChunkInput();
        chunkInput.setAwsBucketName(request.getAwsBucketName());
        chunkInput.setAwsStorageName(request.getAwsStorageName());
        chunkInput.setLength(request.getLength());
        chunkInput.setMediaId(request.getMediaId());
        chunkInput.setResolution(request.getResolution());
        chunkInput.setPosition(request.getPosition());

        chunksMetadataService = CDI.current().select(ChunksMetadataService.class).get();
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = chunksMetadataService.addNewMediaChunk(chunkInput);

        MediachunksmetadataService.MediaChunkInfoResponseRepeated response;

        response = MediachunksmetadataService.MediaChunkInfoResponseRepeated.newBuilder()
                .addAllData(this.buildMediaChunksInfoResponse(linkMediaChunkDTOS))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMediaChunksResolution(MediachunksmetadataService.MediaChunkResolutionRequest request, StreamObserver<MediachunksmetadataService.MediaChunkInfoResponseRepeated> responseObserver) {
        mediaChunkService = CDI.current().select(MediaChunkService.class).get();
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = mediaChunkService.getMediaChunksResolution(request.getMediaId(), request.getResolution());
        MediachunksmetadataService.MediaChunkInfoResponseRepeated response;

        response = MediachunksmetadataService.MediaChunkInfoResponseRepeated.newBuilder()
                .addAllData(this.buildMediaChunksInfoResponse(linkMediaChunkDTOS))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAvailableResolutions(Empty request, StreamObserver<MediachunksmetadataService.ResolutionResponse> responseObserver) {
        mediaChunkService = CDI.current().select(MediaChunkService.class).get();
        List<String> resolutions = mediaChunkService.getAvailableResolutions();

        MediachunksmetadataService.ResolutionResponse response = MediachunksmetadataService.ResolutionResponse.newBuilder()
                .addAllResolutions(resolutions)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void linkMediaWithChunk(MediachunksmetadataService.LinkMediaWithChunkRequest request, StreamObserver<MediachunksmetadataService.LinkMediaChunkResponse> responseObserver) {
        chunksMetadataService = CDI.current().select(ChunksMetadataService.class).get();

        NewLinkInput linkInput = new NewLinkInput();
        linkInput.setMediaId(request.getMediaId());
        linkInput.setPosition(request.getPosition());
        linkInput.setResolution(request.getResolution());
        linkInput.setChunkId(request.getChunkId());
        boolean isMediaLinkedWithChunk = chunksMetadataService.linkMediaAndChunk(linkInput);

        MediachunksmetadataService.LinkMediaChunkResponse response = MediachunksmetadataService.LinkMediaChunkResponse.newBuilder()
                .setData(isMediaLinkedWithChunk)
                .setStatus(isMediaLinkedWithChunk ? 200 : 500)
                .setMessage(isMediaLinkedWithChunk ? "Media has been linked with chunk" : "error when linking media")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMediaChunkInfo(MediachunksmetadataService.GetMediaChunkInfoRequest request, StreamObserver<MediachunksmetadataService.ChunkInfo> responseObserver) {
        chunksMetadataService = CDI.current().select(ChunksMetadataService.class).get();

        List<LinkMediaChunkDTO> chunkInfo = chunksMetadataService.getChunk(request.getChunkId());
        if (chunkInfo.size() == 0) {
            responseObserver.onError(new Throwable("no chunk for chunks id: " + request.getChunkId() + " found"));
            return;
        }

        MediachunksmetadataService.ChunkInfo response = MediachunksmetadataService.ChunkInfo.newBuilder()
                .setChunkId(chunkInfo.get(0).getChunk().getChunkId())
                .setLength(chunkInfo.get(0).getChunk().getLength())
                .setCreatedAt(chunkInfo.get(0).getChunk().getCreatedAt().getTime())
                .setAwsStorageName(chunkInfo.get(0).getChunk().getAwsStorageName())
                .setAwsBucketName(chunkInfo.get(0).getChunk().getAwsBucketName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteLinkedMediaChunks(MediachunksmetadataService.MediaIdRequest request, StreamObserver<MediachunksmetadataService.StatusResponse> responseObserver) {
        mediaChunkService = CDI.current().select(MediaChunkService.class).get();
        boolean isDeleted = mediaChunkService.deleteLinkedMediaChunks(request.getMediaId());
        if (!isDeleted) {
            responseObserver.onError(new Throwable("Chunk not deleted: " + isDeleted));
            responseObserver.onCompleted();
        }

        MediachunksmetadataService.StatusResponse rsp = MediachunksmetadataService.StatusResponse.newBuilder()
                .setMessage("media chunks deleted")
                .setStatus(200)
                .setData(true)
                .build();
        responseObserver.onNext(rsp);
        responseObserver.onCompleted();
    }

    private List<MediachunksmetadataService.MediaChunkInfoResponse> buildMediaChunksInfoResponse(List<LinkMediaChunkDTO> linkMediaChunkDTOS) {
        List<MediachunksmetadataService.MediaChunkInfoResponse> mediaChunkInfoResponses = new ArrayList<MediachunksmetadataService.MediaChunkInfoResponse>();
        for (LinkMediaChunkDTO linkMediaChunkDTO : linkMediaChunkDTOS) {
            mediaChunkInfoResponses.add(
                    MediachunksmetadataService.MediaChunkInfoResponse.newBuilder()
                            .setMediaId(linkMediaChunkDTO.getMediaId())
                            .setResolution(linkMediaChunkDTO.getResolution())
                            .setPosition(linkMediaChunkDTO.getPosition())
                            .setId(linkMediaChunkDTO.getId())
                            .setChunk(
                                    MediachunksmetadataService.ChunkInfo.newBuilder()
                                            .setAwsBucketName(linkMediaChunkDTO.getChunk().getAwsBucketName())
                                            .setAwsStorageName(linkMediaChunkDTO.getChunk().getAwsStorageName())
                                            .setLength(linkMediaChunkDTO.getChunk().getLength())
                                            .setChunkId(linkMediaChunkDTO.getChunk().getChunkId())
                                            .setCreatedAt(linkMediaChunkDTO.getChunk().getCreatedAt().getTime())
                                            .build()
                            )
                            .build()
            );
        }

        return mediaChunkInfoResponses;
    }
}
