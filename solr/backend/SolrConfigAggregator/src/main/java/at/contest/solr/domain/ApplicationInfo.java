package at.contest.solr.domain;

import java.time.LocalDateTime;

public class ApplicationInfo {

    String serviceName;
    String version;
    LocalDateTime buildDate;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDateTime buildDate) {
        this.buildDate = buildDate;
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
                "serviceName='" + serviceName + '\'' +
                ", version='" + version + '\'' +
                ", buildDate=" + buildDate +
                '}';
    }
}
