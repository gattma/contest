package at.contest.solr.service;

import at.contest.solr.application.config.SolrConfig;
import at.contest.solr.domain.Configurations;
import at.contest.solr.rest.clients.SolrSearchServiceClient;
import at.contest.solr.domain.ApplicationInfo;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Traced
@RequestScoped
public class SolrConfigAggregatorService {

    @Inject
    Logger logger;

    @Inject
    SolrConfig config;

    @Inject
    SolrSearchServiceClient solrSearchServiceClient;

    public Configurations aggregateConfigs() {
        logger.info("SolrConfigAggregatorService#aggregateConfigs");
        Configurations configs = new Configurations();
        configs.setSolrServerVersion(getSolrServerVersion());
        configs.addApplicationInfo(solrSearchServiceClient.getConfig());
        return configs;
    }

    private String getSolrServerVersion() {
        logger.debug("SolrConfigAggregatorService#getSolrServerVersion");
        try {
            URL url = new URL(String.format("%s/%s", config.getSolrUrl(), "config"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            String jsonAsString = readToString(url);
            String version = extractVersion(jsonAsString);
            logger.infof("Found solr server version: %s", version);
            return version;
        } catch (IOException | ParseException e) {
            logger.error("cannot read version of solr-Server, reason: ", e);
            return "not readable";
        }
    }

    private String readToString(URL url) throws IOException {
        logger.debug("SolrConfigAggregatorService#readToString");
        StringBuilder inline = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();
        return inline.toString();
    }

    private String extractVersion(String json) throws ParseException {
        JSONParser parse = new JSONParser();
        JSONObject data_obj = (JSONObject) parse.parse(json);
        JSONObject configObj = (JSONObject) data_obj.get("config");
        return configObj.get("luceneMatchVersion").toString();
    }

}
