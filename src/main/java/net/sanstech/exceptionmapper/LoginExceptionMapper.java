package net.sanstech.exceptionmapper;

import net.sanstech.dto.ErrorDTO;
import net.sanstech.exception.SpotitubeLoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<SpotitubeLoginException> {
    @Override
    public Response toResponse(final SpotitubeLoginException message) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorDTO(message.getMessage()))
                .build();
    }
}
