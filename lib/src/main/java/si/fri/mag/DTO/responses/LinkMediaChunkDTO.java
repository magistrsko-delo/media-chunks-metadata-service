package si.fri.mag.DTO.responses;

import si.fri.mag.DTO.abstracts.LinkMediaChunkAbstract;

public class LinkMediaChunkDTO extends LinkMediaChunkAbstract  {
    private Integer id;
    private ChunkDTO chunk;

    public Integer getId() {
        return id;
    }

    public ChunkDTO getChunk() {
        return chunk;
    }

    // setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setChunk(ChunkDTO chunk) {
        this.chunk = chunk;
    }
}
