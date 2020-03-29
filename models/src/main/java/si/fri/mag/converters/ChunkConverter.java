package si.fri.mag.converters;

import si.fri.mag.DTO.input.NewChunkInput;
import si.fri.mag.DTO.responses.ChunkDTO;
import si.fri.mag.entities.ChunkEntity;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;

@ApplicationScoped
public class ChunkConverter {

    public ChunkDTO toDTO(ChunkEntity chunkEntity) {
        ChunkDTO chunkDTO = new ChunkDTO();
        chunkDTO.setChunkId(chunkEntity.getChunkId());
        chunkDTO.setCreatedAt(chunkEntity.getCreatedAt());
        chunkDTO.setAwsBucketName(chunkEntity.getAwsBucketName());
        chunkDTO.setAwsStorageName(chunkEntity.getAwsStorageName());
        chunkDTO.setLength(chunkEntity.getLength());

        return chunkDTO;
    }

    public ChunkEntity toEntity(NewChunkInput newChunkInput) {
        ChunkEntity chunkEntity = new ChunkEntity();
        chunkEntity.setAwsBucketName(newChunkInput.getAwsBucketName());
        chunkEntity.setAwsStorageName(newChunkInput.getAwsStorageName());
        chunkEntity.setLength(newChunkInput.getLength());
        chunkEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        return chunkEntity;
    }

}
