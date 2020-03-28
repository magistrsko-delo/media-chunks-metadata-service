package si.fri.mag.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "chunk")
public class ChunkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chunk_id", nullable = false)
    private Integer chunkId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "aws_bucket_name", nullable = false)
    private String AwsBucketName;

    @Column(name = "aws_storage_name", nullable = false)
    private String awsStorageName;

    @Column(name = "length", nullable = false)
    private Double length;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    // FUTURE
    // IV vector and key for every chunk created

}
