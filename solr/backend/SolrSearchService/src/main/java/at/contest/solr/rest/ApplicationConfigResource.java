package at.contest.solr.rest;

import at.contest.solr.application.config.ApplicationConfig;
import at.contest.solr.model.ApplicationInfo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/info")
public class ApplicationConfigResource {

    @Inject
    ApplicationConfig config;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApplicationInfo get() {
        return ApplicationInfo.builder()
                .serviceName(config.getServiceName())
                .version(config.getVersion())
                .buildDate(config.getBuildDate())
                .build();
    }

}
