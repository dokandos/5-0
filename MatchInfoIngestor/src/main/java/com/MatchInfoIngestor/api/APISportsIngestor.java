package com.MatchInfoIngestor.api;


// API calls from the following website: https://www.api-football.com/documentation-v3
// https://dashboard.api-football.com/

import com.MatchInfoIngestor.api.defaultResponse.fixture.FixtureResponse;
import com.MatchInfoIngestor.api.defaultResponse.league.LeagueResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class APISportsIngestor {
    private final WebClient webClient;
    private final ObjectMapper mapper;

    private int leagueId = 34; //TODO check how to remove this hard-coded id
    private LeagueResponse leagueResponse;

    @Value("${x-rapidapi-host}")
    private String xRapidApiHost;

    @Value("${x-rapidapi-key}")
    private String xRapidApiKey;

    public APISportsIngestor(WebClient.Builder builder, ObjectMapper mapper) {
        webClient = builder.baseUrl("https://v3.football.api-sports.io").build();
        this.mapper = mapper;
    }

    public LeagueResponse getLeagueResponse() {
        return leagueResponse;
    }

    public void setLeagueResponse(LeagueResponse leagueResponse) {
        this.leagueResponse = leagueResponse;
    }

    //TODO improve performance
    public JsonNode getJsonNode(String uri) {
        return webClient
                .get()
                .uri(uri)
                .header("x-rapidapi-host", xRapidApiHost)
                .header("x-rapidapi-key", xRapidApiKey)
                .retrieve()
                .bodyToMono(String.class).map(content -> {
                    try {
                        return mapper.readTree(content);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).block(); //TODO remove this line and make it asynchronous.
    }

    public <T> T getResponse(JsonNode jsonNode, Class<T> targetType) {
        try {
            return mapper.treeToValue(jsonNode, targetType);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LeagueResponse requestLeagueResponse() {
        JsonNode node = getJsonNode("leagues?id="+leagueId+"&last=01").get("response");
        System.out.println(node.toString());
        LeagueResponse response = getResponse(node, LeagueResponse[].class)[0];
        setLeagueResponse(response);
        return response;
    }

    public FixtureResponse[] requestFixtureResponse() {
        int year = leagueResponse == null? 2022:leagueResponse.getSeasons().get(0).getYear(); //TODO remove hard-coded year
        JsonNode node = getJsonNode("fixtures?league="+leagueId+"&season="+year).get("response");
        FixtureResponse[] response = getResponse(node, FixtureResponse[].class);
        //TODO set Fixture Response field?
        return response;
    }
}

