package si.fri.mag.entities;

import si.fri.mag.MainEntity;

import javax.persistence.*;

@Entity
@Table(name = "link_media_chunks")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllChunkInformation",
                query = "SELECT * FROM chunk inner join link_media_chunks on chunk.chunk_id = link_media_chunks.fk_chunk_id",
                resultClass = LinkMediaChunksEntity.class
        ),
})

public class LinkMediaChunksEntity implements MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_chunk_id")
    private ChunkEntity chunkEntity;

    @Column(name = "fk_media_id")
    private Integer mediaId;

    @Column(name = "resolution")
    private String resolution;

    // getters

    public Integer getId() {
        return id;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public ChunkEntity getChunkEntity() {
        return chunkEntity;
    }

    public String getResolution() {
        return resolution;
    }

    // setters
    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public void setChunkEntity(ChunkEntity chunkEntity) {
        this.chunkEntity = chunkEntity;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
