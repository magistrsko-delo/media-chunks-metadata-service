package si.fri.mag.DTO.input;

import si.fri.mag.DTO.abstracts.LinkMediaChunkAbstract;

public class NewLinkInput extends LinkMediaChunkAbstract {
    private Integer chunkId;

    public Integer getChunkId() {
        return chunkId;
    }

    // setters
    public void setChunkId(Integer chunkId) {
        this.chunkId = chunkId;
    }
}
