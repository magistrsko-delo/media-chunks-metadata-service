package si.fri.mag.services;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.entities.LinkMediaChunksEntity;
import si.fri.mag.utils.ConverterUtil;
import si.fri.mag.utils.EntityUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class MediaChunkService {
    @Inject
    private EntityUtils entityUtils;

    @Inject
    private ConverterUtil converterUtil;

    public List<LinkMediaChunkDTO> getMediaChunks(Integer mediaId) {
        Query q = entityUtils.createQuery("getAllMediaChunks").setParameter(1, mediaId);
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        return converterUtil.convertMediaLinkChunkMetadata(linkMediaChunksEntities);
    }

    public List<LinkMediaChunkDTO> getMediaChunksResolution(Integer mediaId, String resolution) {
        Query q = entityUtils.createQuery("getAllMediaChunksResolution").setParameter(1, mediaId).setParameter(2, resolution);
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        return converterUtil.convertMediaLinkChunkMetadata(linkMediaChunksEntities);
    }

    public List<String> getAvailableResolutions() {
        Query q = entityUtils.createQuery("getAvailableResolution");
        return (List<String>)q.getResultList();
    }

}
