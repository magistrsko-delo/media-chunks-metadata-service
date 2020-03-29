package si.fri.mag.DTO.responses;

import si.fri.mag.DTO.abstracts.ChunkAbstract;

import java.util.Date;

public class ChunkDTO extends ChunkAbstract {
    private Integer chunkId;
    private Date createdAt;

    // getters
    public Integer getChunkId() {
        return chunkId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


    // setters

    public void setChunkId(Integer chunkId) {
        this.chunkId = chunkId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
