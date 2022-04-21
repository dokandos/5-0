
package com.MatchInfoIngestor.api.defaultResponse.league;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "events",
    "lineups",
    "statistics_fixtures",
    "statistics_players"
})
@Generated("jsonschema2pojo")
public class Fixtures {

    @JsonProperty("events")
    private Boolean events;
    @JsonProperty("lineups")
    private Boolean lineups;
    @JsonProperty("statistics_fixtures")
    private Boolean statisticsFixtures;
    @JsonProperty("statistics_players")
    private Boolean statisticsPlayers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("events")
    public Boolean getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(Boolean events) {
        this.events = events;
    }

    @JsonProperty("lineups")
    public Boolean getLineups() {
        return lineups;
    }

    @JsonProperty("lineups")
    public void setLineups(Boolean lineups) {
        this.lineups = lineups;
    }

    @JsonProperty("statistics_fixtures")
    public Boolean getStatisticsFixtures() {
        return statisticsFixtures;
    }

    @JsonProperty("statistics_fixtures")
    public void setStatisticsFixtures(Boolean statisticsFixtures) {
        this.statisticsFixtures = statisticsFixtures;
    }

    @JsonProperty("statistics_players")
    public Boolean getStatisticsPlayers() {
        return statisticsPlayers;
    }

    @JsonProperty("statistics_players")
    public void setStatisticsPlayers(Boolean statisticsPlayers) {
        this.statisticsPlayers = statisticsPlayers;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Fixtures{" +
                "events=" + events +
                ", lineups=" + lineups +
                ", statisticsFixtures=" + statisticsFixtures +
                ", statisticsPlayers=" + statisticsPlayers +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
