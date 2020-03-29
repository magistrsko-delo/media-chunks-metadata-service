package si.fri.mag.DTO.responses;

import si.fri.mag.DTO.ChunkAbstract;

import java.util.Date;

public class ChunkDTO extends ChunkAbstract {
    private Integer chunkId;
    private Date createdAt;
    private Date updatedAt;

    // getter
    public Integer getChunkId() {
        return chunkId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    // setters

    public void setChunkId(Integer chunkId) {
        this.chunkId = chunkId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
