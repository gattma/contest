package at.contest.solr.rest;

import at.contest.solr.domain.Configurations;
import at.contest.solr.rest.clients.SolrSearchServiceClient;
import at.contest.solr.service.SolrConfigAggregatorService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class SolrConfigAggregatorResource {

    @Inject
    Logger logger;

    @Inject
    SolrConfigAggregatorService configAggregatorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Configurations get() {
        logger.info("SolrConfigAggregatorResource#get()");
        Configurations configs = configAggregatorService.aggregateConfigs();
        logger.debugf("Found following configurations: %s", configs.toString());
        return configs;
    }
}