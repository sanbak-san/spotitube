package net.sanstech.exceptionmapper;

import net.sanstech.dto.ErrorDTO;
import net.sanstech.exception.SpotitubeTokenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TokenExceptionMapper implements ExceptionMapper<SpotitubeTokenException> {
    @Override
    public Response toResponse(final SpotitubeTokenException e) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorDTO(e.getMessage()))
                .build();
    }
}
