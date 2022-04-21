
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
    "name",
    "code",
    "flag"
})
@Generated("jsonschema2pojo")
public class Country {

    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private Object code;
    @JsonProperty("flag")
    private Object flag;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("code")
    public Object getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Object code) {
        this.code = code;
    }

    @JsonProperty("flag")
    public Object getFlag() {
        return flag;
    }

    @JsonProperty("flag")
    public void setFlag(Object flag) {
        this.flag = flag;
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
        return "Country{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", flag=" + flag +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
