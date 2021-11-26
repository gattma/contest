package at.contest.solr.application.producer;

import at.contest.solr.application.config.SolrConfig;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class SolrClientProducer {

    @Inject
    SolrConfig config;

    @Produces
    public HttpSolrClient produceSolrClient() {
        HttpSolrClient solrClient = new HttpSolrClient.Builder(config.getSolrUrl()).build();
        solrClient.setParser(new XMLResponseParser());
        return solrClient;
    }

}
