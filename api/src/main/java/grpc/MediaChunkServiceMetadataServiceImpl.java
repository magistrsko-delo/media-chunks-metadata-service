package grpc;

import com.google.protobuf.Empty;
import com.kumuluz.ee.grpc.annotations.GrpcService;
import io.grpc.stub.StreamObserver;
import si.fri.mag.DTO.input.NewChunkInput;
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
