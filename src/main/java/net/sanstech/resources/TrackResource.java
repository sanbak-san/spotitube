package net.sanstech.resources;

import net.sanstech.persistence.TrackDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {
    private TrackDAO trackDAO = new TrackDAO();

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllTracks() {
//
//        return Response.ok().entity(trackDAO.getTrack(1)).build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@QueryParam("forPlaylist") int playlist_id) {
        return Response.ok().entity(trackDAO.getTracksByPlaylist(playlist_id)).build();
    }
}