package si.fri.mag.services;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.entities.LinkMediaChunksEntity;
import si.fri.mag.utils.ConverterUtil;
import si.fri.mag.utils.EntityUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;

@RequestScoped
public class ChunksMetadataService {

    @Inject
    private EntityUtils entityUtils;

    @Inject
    private ConverterUtil converterUtil;

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


}
