package at.contest.solr.application.producer;

import at.contest.solr.rest.clients.SolrSearchServiceClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.MalformedURLException;
import java.net.URL;

@ApplicationScoped
public class RestClientProducer {

    @ConfigProperty(name = "solrSearchService.baseURL", defaultValue = "http://localhost:8080")
    String baseURLSolrSearchService;

    @Produces
    public SolrSearchServiceClient produceSolrSearchServiceClient() throws MalformedURLException {
        return RestClientBuilder.newBuilder()
                .baseUrl(new URL(baseURLSolrSearchService))
                .build(SolrSearchServiceClient.class);
    }


}
