package si.fri.mag.DTO.input;

import si.fri.mag.DTO.ChunkAbstract;

public class NewChunkInput extends ChunkAbstract {

    private Integer mediaId;
    private String resolution;
    private Integer position;

    public Integer getMediaId() {
        return mediaId;
    }

    public String getResolution() {
        return resolution;
    }

    public Integer getPosition() {
        return position;
    }

    // setters
    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
