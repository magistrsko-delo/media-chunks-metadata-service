package si.fri.mag.converters;

import si.fri.mag.DTO.interfaces.LinkMedia;
import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.entities.ChunkEntity;
import si.fri.mag.entities.LinkMediaChunksEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LinkMediaChunkConverter {

    @Inject
    private ChunkConverter chunkConverter;

    public LinkMediaChunkDTO toDTO(LinkMediaChunksEntity linkMediaChunksEntity) {
        LinkMediaChunkDTO linkMediaChunkDTO = new LinkMediaChunkDTO();
        linkMediaChunkDTO.setId(linkMediaChunksEntity.getId());
        linkMediaChunkDTO.setMediaId(linkMediaChunksEntity.getMediaId());
        linkMediaChunkDTO.setResolution(linkMediaChunksEntity.getResolution());
        linkMediaChunkDTO.setPosition(linkMediaChunksEntity.getPosition());
        linkMediaChunkDTO.setChunk(chunkConverter.toDTO(linkMediaChunksEntity.getChunkEntity()));

        return linkMediaChunkDTO;
    }

    public LinkMediaChunksEntity toEntity(ChunkEntity chunkEntity, LinkMedia linkMedia){

        LinkMediaChunksEntity linkMediaChunksEntity = new LinkMediaChunksEntity();
        linkMediaChunksEntity.setChunkEntity(chunkEntity);
        linkMediaChunksEntity.setMediaId(linkMedia.getMediaId());
        linkMediaChunksEntity.setResolution(linkMedia.getResolution());
        linkMediaChunksEntity.setPosition(linkMedia.getPosition());
        return linkMediaChunksEntity;
    }

}
