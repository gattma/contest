package at.contest;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.junit.Test;

import java.io.IOException;

public class CreateCoreTest {

    private final String solrUrl = "http://solr-contest.apps.play.gepaplexx.com/solr/";

    @Test
    public void test() throws SolrServerException, IOException {
        // TODO initContainer mit oc exec openshift-solr-7cb8c87bd8-x85w4 -- mkdir /var/solr/data/contest-core
        // TODO initContainer mit oc exec openshift-solr-7cb8c87bd8-x85w4 -- cp /opt/solr-8.8.1/example/files/conf/ /var/solr/data/contest-core -r
        var client = new HttpSolrClient.Builder(solrUrl).build();
        CoreAdminRequest.createCore("contest-core", null, client);
    }

}
