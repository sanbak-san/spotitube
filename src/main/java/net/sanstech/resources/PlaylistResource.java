package net.sanstech.resources;

import com.mysql.cj.util.StringUtils;
import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllPlaylists(final @QueryParam("token") String token) {
        if (StringUtils.isNullOrEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().entity(playlistService.getAllPlaylists(token)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(final @QueryParam("token") String token, final @PathParam("id") int id) {
        if (StringUtils.isNullOrEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().entity(playlistService.deletePlaylist(token, id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(final @QueryParam("token") String token, final PlaylistDTO playlistDTO) {
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
//        playlistSummaryDTO.addPlaylist(playlistDTO);

        return Response.ok().entity(playlistSummaryDTO).build();
    }

//    @PUT
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updatePlaylistName(PathParam("id") int id, PlaylistDTO playlistDTO){
//
//    }
}
