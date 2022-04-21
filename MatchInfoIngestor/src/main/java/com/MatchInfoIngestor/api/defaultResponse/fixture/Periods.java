
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
    "first",
    "second"
})
@Generated("jsonschema2pojo")
public class Periods {

    @JsonProperty("first")
    private Integer first;
    @JsonProperty("second")
    private Integer second;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("first")
    public Integer getFirst() {
        return first;
    }

    @JsonProperty("first")
    public void setFirst(Integer first) {
        this.first = first;
    }

    @JsonProperty("second")
    public Integer getSecond() {
        return second;
    }

    @JsonProperty("second")
    public void setSecond(Integer second) {
        this.second = second;
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
