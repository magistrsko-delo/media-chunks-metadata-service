package si.fri.mag.mappers;

import si.fri.mag.controllers.MainController;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorMapper extends MainController implements ExceptionMapper<InternalServerErrorException> {
    @Override
    public Response toResponse(InternalServerErrorException e) {
        return this.responseError(500, e.getMessage());
    }
}
