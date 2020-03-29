package si.fri.mag.DTO.abstracts;

import si.fri.mag.DTO.interfaces.LinkMedia;

public abstract class LinkMediaChunkAbstract implements LinkMedia {
    protected Integer mediaId;
    protected String resolution;
    protected Integer position;

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
