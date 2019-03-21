package net.sanstech.resources;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
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

    public LoginResource() {
    }

    @Inject
    public LoginResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(UserDTO user) {
        TokenDTO token = authenticationService.login(user.getUser(), user.getPassword());
        return Response.ok(token).build();
//        UserDTO authenticatedUser = userDAO.getUser(user.getUser(), user.getPassword());
//
//        if (authenticatedUser != null) {
//            return Response.ok().entity(tokenDAO.getToken(user.getUser())).build();
//        } else {
//            return Response.ok().entity(new ErrorDTO("Login failed for user.")).build();
//        }
    }
}
