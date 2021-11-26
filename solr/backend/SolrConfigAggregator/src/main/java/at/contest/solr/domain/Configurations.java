package at.contest.solr.domain;

import java.util.ArrayList;
import java.util.List;

public class Configurations {

    String solrServerVersion;
    List<ApplicationInfo> configs;

    public void addApplicationInfo(ApplicationInfo info) {
        if (configs == null) {
            configs = new ArrayList<>();
        }

        configs.add(info);
    }

    public String getSolrServerVersion() {
        return solrServerVersion;
    }

    public void setSolrServerVersion(String solrServerVersion) {
        this.solrServerVersion = solrServerVersion;
    }

    public List<ApplicationInfo> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ApplicationInfo> configs) {
        this.configs = configs;
    }

    @Override
    public String toString() {
        return "Configurations{" +
                "solrServerVersion='" + solrServerVersion + '\'' +
                ", configs=" + configs +
                '}';
    }
}
