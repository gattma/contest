package at.contest.solr.service;

import at.contest.solr.model.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Traced
@RequestScoped
public class SolrSearchService {

    @Inject
    Logger logger;

    @Inject
    HttpSolrClient client;

    public List<SearchResult> findByText(String text) throws SolrServerException, IOException {
        logger.infof("findByText(%s)", text);
        SolrQuery query = new SolrQuery();
        query.set("q", String.format("text:%s", text));
        logger.debugf("search with query 'text:%s'", text);
        QueryResponse response = client.query(query);

        SolrDocumentList docList = response.getResults();
        logger.infof("NumFound: %d", docList.getNumFound());

        var resultList = new ArrayList<SearchResult>();
        for (SolrDocument doc : docList) {
            logger.debugf("found in title: %s", doc.getFieldValue("title"));
            resultList.add(new SearchResult(
                    (String) doc.getFieldValue("title"),
                    (String) doc.getFieldValue("subject"))
            );
        }

        return resultList;
    }
}
