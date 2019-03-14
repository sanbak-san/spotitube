package net.sanstech;

import net.sanstech.dto.ErrorDTO;
import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
import net.sanstech.persistence.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private UserDAO userDAO = new UserDAO();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(UserDTO user) {
        UserDTO authenticatedUser = UserDAO.getUser(user.getUser(), user.getPassword());

        if (authenticatedUser != null) {
            return Response.ok().entity(new TokenDTO(user)).build();
        } else {
            return Response.ok().entity(new ErrorDTO("Login failed for user.")).build();
        }
    }
}
