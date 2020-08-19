package net.sanstech.exceptionmapper;

import net.sanstech.dto.ErrorDTO;
import net.sanstech.exception.SpotitubePersistenceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<SpotitubePersistenceException> {
    @Override
    public Response toResponse(final SpotitubePersistenceException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO("Database connection error. Please try again later."))
                .build();
    }
}
