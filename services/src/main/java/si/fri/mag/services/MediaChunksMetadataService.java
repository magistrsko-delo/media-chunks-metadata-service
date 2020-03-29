package si.fri.mag.services;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.converters.LinkMediaChunkConverter;
import si.fri.mag.entities.ChunkEntity;
import si.fri.mag.entities.LinkMediaChunksEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class MediaChunksMetadataService {
    @Inject
    private EntityManager em;

    @Inject
    private LinkMediaChunkConverter linkMediaChunkConverter;

    public List<LinkMediaChunkDTO> getAllMediaChunks(){
        Query q = em.createNamedQuery("getAllChunkInformation");
        List<LinkMediaChunksEntity> linkMediaChunksEntities = (List<LinkMediaChunksEntity>)q.getResultList();

        List<LinkMediaChunkDTO> linkMediaChunkDTOS = new ArrayList<LinkMediaChunkDTO>();
        for(LinkMediaChunksEntity linkMediaChunk : linkMediaChunksEntities) {
            linkMediaChunkDTOS.add(linkMediaChunkConverter.toDTO(linkMediaChunk));
        }

        return linkMediaChunkDTOS;
    }

}
