package si.fri.mag.entities;

import si.fri.mag.interfaces.MainEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "chunk")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllChunks",
                query = "",
                resultClass = ChunkEntity.class
        ),
})

public class ChunkEntity implements MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chunk_id", nullable = false)
    private Integer chunkId;

    @Column(name = "aws_bucket_name", nullable = false)
    private String AwsBucketName;

    @Column(name = "aws_storage_name", nullable = false)
    private String awsStorageName;

    @Column(name = "length", nullable = false)
    private Double length;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    // getters
    public Integer getChunkId() {
        return chunkId;
    }

    public Double getLength() {
        return length;
    }

    public String getAwsBucketName() {
        return AwsBucketName;
    }

    public String getAwsStorageName() {
        return awsStorageName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // setters

    public void setAwsBucketName(String awsBucketName) {
        AwsBucketName = awsBucketName;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setAwsStorageName(String awsStorageName) {
        this.awsStorageName = awsStorageName;
    }

    public void setChunkId(Integer chunkId) {
        this.chunkId = chunkId;
    }

    // FUTURE
    // IV vector and key for every chunk created

}
