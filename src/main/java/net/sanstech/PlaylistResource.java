package net.sanstech;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistsDTO;
import net.sanstech.persistence.PlaylistDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    //private PlaylistsDTO playlistsDTO = null;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllPlaylists() {
        return Response.ok().entity(new PlaylistsDTO()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.removePlaylist(id);
        return Response.ok().entity(playlistsDTO).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.addPlaylist(playlistDTO);

        return Response.ok().entity(playlistsDTO).build();
    }

//    @PUT
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updatePlaylistName(PathParam("id") int id, PlaylistDTO playlistDTO){
//
//    }
}
