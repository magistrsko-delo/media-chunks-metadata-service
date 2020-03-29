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

        @NamedNativeQuery(
                name = "getChunkInformation",
                query = "SELECT * FROM chunk inner join link_media_chunks on chunk.chunk_id = link_media_chunks.fk_chunk_id where chunk.chunk_id = ?1",
                resultClass = LinkMediaChunksEntity.class
        ),

        @NamedNativeQuery(
                name = "getAllMediaChunks",
                query = "SELECT * FROM chunk inner join link_media_chunks on chunk.chunk_id = link_media_chunks.fk_chunk_id where link_media_chunks.fk_media_id = ?1 order by link_media_chunks.resolution, link_media_chunks.position asc",
                resultClass = LinkMediaChunksEntity.class
        ),

        @NamedNativeQuery(
                name = "getAllMediaChunksResolution",
                query = "SELECT * FROM chunk inner join link_media_chunks on chunk.chunk_id = link_media_chunks.fk_chunk_id where link_media_chunks.fk_media_id = ?1 and link_media_chunks.resolution = ?2 order by link_media_chunks.position asc",
                resultClass = LinkMediaChunksEntity.class
        ),

        @NamedNativeQuery(
                name = "getAvailableResolution",
                query = "SELECT distinct linkchunk.resolution FROM link_media_chunks linkchunk"
        ),
})

public class LinkMediaChunksEntity implements MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_chunk_id", nullable = false)
    private ChunkEntity chunkEntity;

    @Column(name = "fk_media_id", nullable = false)
    private Integer mediaId;

    @Column(name = "resolution", nullable = false)
    private String resolution;

    @Column(name = "position", nullable = false)
    private Integer position;

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

    public Integer getPosition() {
        return position;
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

    public void setPosition(Integer position) {
        this.position = position;
    }
}
