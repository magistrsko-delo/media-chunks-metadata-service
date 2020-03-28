package si.fri.mag.services;

import si.fri.mag.entities.ChunkEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
public class MediaChunksMetadataService {
    @Inject
    private EntityManager em;

    public void getAllMediaChunks(){
        System.out.println("GETTING ALL MEDIA CHUNKS");
        ChunkEntity chunkEntity = em.find(ChunkEntity.class, 1);
        System.out.println(chunkEntity);
    }

}
