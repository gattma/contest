package at.contest.solr.service;

import at.contest.solr.model.Configurations;
import at.contest.solr.model.SearchResult;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@Traced
@RequestScoped
public interface SolrFrontendFacadeServiceAPI {

    List<SearchResult> findByText(String text);

    Configurations readConfig();

}
