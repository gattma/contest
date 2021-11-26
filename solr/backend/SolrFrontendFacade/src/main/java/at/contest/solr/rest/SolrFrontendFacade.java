package at.contest.solr.rest;

import at.contest.solr.model.Configurations;
import at.contest.solr.model.SearchResult;
import at.contest.solr.service.SolrFrontendFacadeService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class SolrFrontendFacade {

    @Inject
    Logger logger;

    @Inject
    SolrFrontendFacadeService facadeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("search")
    public List<SearchResult> findByText(@QueryParam("text") String text) {
        logger.infof("SolrFrontendFacade#findByText(%s)", text);
        var res = facadeService.findByText(text);
        logger.debugf("Found %d results.", res.size());
        return res;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("config")
    public Configurations getConfig() {
        logger.info("SolrFrontendFacade#getConfig()");
        return facadeService.readConfig();
    }
}