package si.fri.mag.utils;

import si.fri.mag.entities.LinkMediaChunksEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class EntityUtils {
    @Inject
    private EntityManager em;

    public Query createQuery(String queryName) {
        return em.createNamedQuery(queryName);
    }


}
