
package com.MatchInfoIngestor.api.defaultResponse.fixture;

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
    "halftime",
    "fulltime",
    "extratime",
    "penalty"
})
@Generated("jsonschema2pojo")
public class Score {

    @JsonProperty("halftime")
    private Halftime halftime;
    @JsonProperty("fulltime")
    private Fulltime fulltime;
    @JsonProperty("extratime")
    private Extratime extratime;
    @JsonProperty("penalty")
    private Penalty penalty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("halftime")
    public Halftime getHalftime() {
        return halftime;
    }

    @JsonProperty("halftime")
    public void setHalftime(Halftime halftime) {
        this.halftime = halftime;
    }

    @JsonProperty("fulltime")
    public Fulltime getFulltime() {
        return fulltime;
    }

    @JsonProperty("fulltime")
    public void setFulltime(Fulltime fulltime) {
        this.fulltime = fulltime;
    }

    @JsonProperty("extratime")
    public Extratime getExtratime() {
        return extratime;
    }

    @JsonProperty("extratime")
    public void setExtratime(Extratime extratime) {
        this.extratime = extratime;
    }

    @JsonProperty("penalty")
    public Penalty getPenalty() {
        return penalty;
    }

    @JsonProperty("penalty")
    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
