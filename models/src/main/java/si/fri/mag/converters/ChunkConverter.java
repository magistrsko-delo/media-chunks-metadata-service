package si.fri.mag.converters;

import si.fri.mag.DTO.responses.ChunkDTO;
import si.fri.mag.entities.ChunkEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ChunkConverter {

    public ChunkDTO toDTO(ChunkEntity chunkEntity) {

        ChunkDTO chunkDTO = new ChunkDTO();
        chunkDTO.setChunkId(chunkEntity.getChunkId());
        chunkDTO.setCreatedAt(chunkEntity.getCreatedAt());
        chunkDTO.setUpdatedAt(chunkEntity.getUpdatedAt());
        chunkDTO.setAwsBucketName(chunkEntity.getAwsBucketName());
        chunkDTO.setAwsStorageName(chunkEntity.getAwsStorageName());
        chunkDTO.setLength(chunkEntity.getLength());

        return chunkDTO;
    }

}
