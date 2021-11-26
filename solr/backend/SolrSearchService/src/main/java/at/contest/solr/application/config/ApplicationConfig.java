package at.contest.solr.application.config;

import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
@Getter
public class ApplicationConfig {

    @Inject
    @ConfigProperty(name = "quarkus.jaeger.service-name")
    String serviceName;

    @Inject
    @ConfigProperty(name = "build.version")
    String version;

    @Inject
    @ConfigProperty(name = "build.date")
    LocalDateTime buildDate;
}
