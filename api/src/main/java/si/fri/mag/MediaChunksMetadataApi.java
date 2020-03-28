package si.fri.mag;

import si.fri.mag.controllers.v1.MediaChunksMetadataController;
import si.fri.mag.mappers.EntityNotFoundMapper;
import si.fri.mag.mappers.ForbiddenExceptionMapper;
import si.fri.mag.mappers.NotFoundExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MediaChunksMetadataApi extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(EntityNotFoundMapper.class);
        resources.add(ForbiddenExceptionMapper.class);
        resources.add(NotFoundExceptionMapper.class);
        resources.add(MediaChunksMetadataController.class);
        return resources;
    }
}
