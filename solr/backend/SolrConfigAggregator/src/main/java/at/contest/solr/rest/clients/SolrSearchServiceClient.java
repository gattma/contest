package at.contest.solr.rest.clients;

import at.contest.solr.domain.ApplicationInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SolrSearchServiceClient {

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    ApplicationInfo getConfig();

}
