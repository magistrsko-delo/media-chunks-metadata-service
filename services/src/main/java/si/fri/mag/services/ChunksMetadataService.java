package si.fri.mag.services;

import si.fri.mag.DTO.input.NewChunkInput;
import si.fri.mag.DTO.input.NewLinkInput;
import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.converters.ChunkConverter;
import si.fri.mag.converters.LinkMediaChunkConverter;
import si.fri.mag.entities.ChunkEntity;
import si.fri.mag.entities.LinkMediaChunksEntity;
import si.fri.mag.utils.ConverterUtil;
import si.fri.mag.utils.EntityUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.ws.rs.InternalServerErrorException;
import java.util.List;

@ApplicationScoped
public class ChunksMetadataService {

    @Inject
    EntityManager em;

    @Inject
    private EntityUtils entityUtils;

    @Inject
    private ConverterUtil converterUtil;

    @Inject
    private ChunkConverter chunkConverter;

    @Inject
    private LinkMediaChunkConverter linkMediaChunkConverter;

    public List<LinkMediaChunkDTO> getAllChunks(){
        Query q = entityUtils.createQuery("getAllChunkInformation");
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        return converterUtil.convertMediaLinkChunkMetadata(linkMediaChunksEntities);
    }

    public List<LinkMediaChunkDTO> getChunk(Integer chunkId){
        Query q = entityUtils.createQuery("getChunkInformation").setParameter(1, chunkId);
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        return converterUtil.convertMediaLinkChunkMetadata(linkMediaChunksEntities);
    }

    public List<LinkMediaChunkDTO> addNewMediaChunk(NewChunkInput chunkInput) {

        ChunkEntity chunkEntity = chunkConverter.toEntity(chunkInput);
        chunkEntity = (ChunkEntity) entityUtils.createNewEntity(chunkEntity);

        if (chunkEntity == null) {
            throw new InternalServerErrorException("failed to create new chunk entity");
        }

        LinkMediaChunksEntity linkMediaChunksEntity = linkMediaChunkConverter.toEntity(chunkEntity, chunkInput);
        linkMediaChunksEntity = (LinkMediaChunksEntity) entityUtils.createNewEntity(linkMediaChunksEntity);

        if (linkMediaChunksEntity == null) {
            throw new InternalServerErrorException("failed to create new link media chunk entity");
        }

        // get newly created entity.
        Query q = entityUtils.createQuery("getChunkInformation").setParameter(1, chunkEntity.getChunkId());
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        return converterUtil.convertMediaLinkChunkMetadata(linkMediaChunksEntities);
    }

    public boolean linkMediaAndChunk(NewLinkInput linkInput) {

        ChunkEntity chunkEntity = em.find(ChunkEntity.class, linkInput.getChunkId());

        if (chunkEntity == null) {
            throw new EntityNotFoundException("chunks entity with id: " + linkInput.getChunkId() + " not found");
        }

        LinkMediaChunksEntity linkMediaChunksEntity = linkMediaChunkConverter.toEntity(chunkEntity, linkInput);
        linkMediaChunksEntity = (LinkMediaChunksEntity) entityUtils.createNewEntity(linkMediaChunksEntity);

        if (linkMediaChunksEntity == null) {
            throw new InternalServerErrorException("failed to create new link media chunk entity");
        }

        return true;
    }

}
