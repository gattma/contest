package at.contest.solr.application.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SolrConfig {

    @ConfigProperty(name = "solr.hostPort")
    String solrHostPort;

    @ConfigProperty(name = "solr.core")
    String solrCore;

    public String getSolrUrl() {
        return String.format(
                "http://%s/solr/%s",
                solrHostPort,
                solrCore
        );
    }

    public String getSolrHostPort() {
        return solrHostPort;
    }

    public String getSolrCore() {
        return solrCore;
    }
}
