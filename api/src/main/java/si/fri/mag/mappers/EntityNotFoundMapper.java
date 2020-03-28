package si.fri.mag.mappers;

import si.fri.mag.controllers.MainController;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.persistence.EntityNotFoundException;

@Provider
public class EntityNotFoundMapper extends MainController implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException e) {
        return this.responseError(404, e.getMessage());
    }
}
