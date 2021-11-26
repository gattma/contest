package at.contest.solr.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@EqualsAndHashCode
@Getter
public class ApplicationInfo {

    private final String serviceName;
    private final String version;
    private final LocalDateTime buildDate;
}
