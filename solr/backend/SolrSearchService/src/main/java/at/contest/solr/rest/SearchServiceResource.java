package at.contest.solr.rest;

import at.contest.solr.model.SearchResult;
import at.contest.solr.service.SolrSearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/SolrSearchService")
public class SearchServiceResource {

    @Inject
    Logger logger;

    @Inject
    SolrSearchService searchService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SearchResult> findByText(@QueryParam("text") String text) throws SolrServerException, IOException {
        logger.infof("findByText(%s)", text);
        try {
            var result = searchService.findByText(text);
            logger.debugf("found %d results", result.size());
            return result;
        } catch (SolrServerException | IOException e) {
            logger.error(e);
            throw e;
        }
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }


}