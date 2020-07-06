package net.sanstech.resources;

import com.mysql.cj.util.StringUtils;
import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.TrackDTO;
import net.sanstech.exception.SpotitubeTokenException;
import net.sanstech.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(final PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllPlaylists(final @QueryParam("token") String token) {
        if (StringUtils.isNullOrEmpty(token)) {
            throw new SpotitubeTokenException();
        }

        return Response.ok().entity(playlistService.getAllPlaylists(token)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(final @QueryParam("token") String token, final @PathParam("id") int id) {
        if (StringUtils.isNullOrEmpty(token)) {
            throw new SpotitubeTokenException();
        }

        return Response.ok().entity(playlistService.deletePlaylist(token, id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(final @QueryParam("token") String token, final PlaylistDTO playlistDTO) {
        if (StringUtils.isNullOrEmpty(token)) {
            throw new SpotitubeTokenException();
        }

        return Response.ok().entity(playlistService.addPlaylist(token, playlistDTO)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylistName(final @QueryParam("token") String token, final PlaylistDTO playlistDTO) {
        if (StringUtils.isNullOrEmpty(token)) {
            throw new SpotitubeTokenException();
        }

        return Response.ok().entity(playlistService.editPlaylist(token, playlistDTO)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTracksForPlaylist(final @QueryParam("token") String token, final @PathParam("id") int id) {
        if (StringUtils.isNullOrEmpty(token)) {
            throw new SpotitubeTokenException();
        }

        return Response.ok().entity(playlistService.getTracks(id)).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(final @QueryParam("token") String token, final @PathParam("id") int id, final TrackDTO trackDTO){
        if (StringUtils.isNullOrEmpty(token)) {
            throw new SpotitubeTokenException();
        }

        return Response.ok().entity(playlistService.addTrackToPlaylist(id, trackDTO)).build();
    }

    @DELETE
    @Path("/{playlist_id}/tracks/{track_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(final @QueryParam("token") String token, final @PathParam("playlist_id") int playlistId, final @PathParam("track_id") int trackId){
        if (StringUtils.isNullOrEmpty(token)) {
            throw new SpotitubeTokenException();
        }

        return Response.ok().entity(playlistService.removeTrackFromPlaylist(playlistId, trackId)).build();
    }
}
