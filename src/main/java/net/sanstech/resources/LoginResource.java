package net.sanstech.resources;

import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubeLoginException;
import net.sanstech.exceptionmapper.LoginExceptionMapper;
import net.sanstech.service.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private AuthenticationService authenticationService;

    private final LoginExceptionMapper loginExceptionMapper = new LoginExceptionMapper();

    @Inject
    public LoginResource(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(final UserDTO user) {
        try {
            return Response.ok().entity(authenticationService.login(user.getUser(), user.getPassword())).build();
        } catch (final SpotitubeLoginException e) {
            return loginExceptionMapper.toResponse(e);
        }
    }
}
