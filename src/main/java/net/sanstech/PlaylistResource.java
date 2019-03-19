package net.sanstech;

import net.sanstech.dto.PlaylistsDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllPlaylists() {

        return Response.ok().entity(new PlaylistsDTO()).build();
    }

//    @DELETE
//    @Path("/{id}")
//    public Response deletePlaylist(@PathParam("id") int id) {
//
//    }
}
