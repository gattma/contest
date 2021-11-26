package at.contest.solr.rest.clients;

import at.contest.solr.model.SearchResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface SolrSearchServiceClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findByText")
    List<SearchResult> findByText(@QueryParam("text") String text);

}
