package si.fri.mag.DTO.responses;

public class LinkMediaChunkDTO {
    private Integer id;
    private Integer mediaId;
    private String resolution;
    private ChunkDTO chunk;

    public Integer getId() {
        return id;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public String getResolution() {
        return resolution;
    }

    public ChunkDTO getChunk() {
        return chunk;
    }

    // setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setChunk(ChunkDTO chunk) {
        this.chunk = chunk;
    }

}
