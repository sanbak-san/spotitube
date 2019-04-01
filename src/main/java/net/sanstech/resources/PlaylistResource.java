package net.sanstech.resources;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistsDTO;
import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TracksDTO;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    @Inject
    PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllPlaylists(@QueryParam("token") String token) {
        return Response.ok().entity(playlistService.getPlaylistsDTO()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        return Response.ok().entity(playlistService.deletePlaylist(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        return Response.ok().entity(playlistService.addPlaylist(playlistDTO)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@PathParam("id") int playlist_id, @QueryParam("token") String token) {

        TracksDTO tracksByPlaylistId = playlistService.getTracksByPlaylistId(playlist_id);
        System.out.println(tracksByPlaylistId);
        return Response.ok().entity(tracksByPlaylistId).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylistName(@PathParam("id") int id, PlaylistDTO playlistDTO) {
        return Response.ok().entity(playlistService.renamePlaylist(playlistDTO)).build();

    }

    @DELETE
    @Path("/{playlist_id}/tracks/{track_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("playlist_id") int playlist_id, @PathParam("track_id") int track_id) {
        return Response.ok().entity(playlistService.deleteTrackFromPlaylist(playlist_id, track_id)).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int playlist_id, TrackDTO trackDTO) {
        return Response.ok().entity(playlistService.addTrackToPlaylist(playlist_id, trackDTO)).build();
    }
}
