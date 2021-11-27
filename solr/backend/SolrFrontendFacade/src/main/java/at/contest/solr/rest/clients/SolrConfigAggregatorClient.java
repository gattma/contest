package at.contest.solr.rest.clients;

import at.contest.solr.model.Configurations;
import at.contest.solr.model.SearchResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface SolrConfigAggregatorClient {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Configurations getConfig();

}
