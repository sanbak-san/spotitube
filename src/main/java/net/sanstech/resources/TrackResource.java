package net.sanstech.resources;

import com.mysql.cj.util.StringUtils;
import net.sanstech.persistence.impl.TrackDAOImpl;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    @Inject
    private TrackDAOImpl trackDAOImpl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(final @QueryParam("token") String token, final @QueryParam("forPlaylist") int forPlaylist) {
        if (StringUtils.isNullOrEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().entity(trackDAOImpl.getAllTracksForPlaylist(forPlaylist)).build();
    }

}
