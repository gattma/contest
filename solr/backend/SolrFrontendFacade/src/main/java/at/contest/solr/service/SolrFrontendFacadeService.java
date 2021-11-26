package at.contest.solr.service;

import at.contest.solr.model.Configurations;
import at.contest.solr.model.SearchResult;
import at.contest.solr.rest.clients.SolrConfigAggregatorClient;
import at.contest.solr.rest.clients.SolrSearchServiceClient;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Traced
@RequestScoped
public class SolrFrontendFacadeService {

    @Inject
    Logger logger;

    @Inject
    SolrSearchServiceClient searchServiceClient;

    @Inject
    SolrConfigAggregatorClient configAggregatorClient;

    //@Override
    public List<SearchResult> findByText(String text) {
        logger.infof("SolrFrontendFacadeService#findByText(%s)", text);
        return searchServiceClient.findByText(text);
    }

    //@Override
    public Configurations readConfig() {
        logger.info("SolrFrontendFacadeService#readConfig()");
        return configAggregatorClient.getConfig();
    }
}
