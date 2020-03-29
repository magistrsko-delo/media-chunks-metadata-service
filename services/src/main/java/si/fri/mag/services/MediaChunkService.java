package si.fri.mag.services;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.entities.LinkMediaChunksEntity;
import si.fri.mag.utils.ConverterUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequestScoped
public class MediaChunkService {
    @Inject
    private EntityManager em;

    @Inject
    private ConverterUtil converterUtil;

    public List<LinkMediaChunkDTO> getMediaChunks(Integer mediaId) {
        Query q = em.createNamedQuery("getAllMediaChunks").setParameter(1, mediaId);
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        return converterUtil.convertMediaLinkChunkMetadata(linkMediaChunksEntities);
    }

    public List<LinkMediaChunkDTO> getMediaChunksResolution(Integer mediaId, String resolution) {
        Query q = em.createNamedQuery("getAllMediaChunksResolution").setParameter(1, mediaId).setParameter(2, resolution);
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        return converterUtil.convertMediaLinkChunkMetadata(linkMediaChunksEntities);
    }

}
