package com.MatchInfoIngestor.api;

import com.MatchInfoIngestor.api.defaultResponse.fixture.FixtureResponse;
import com.MatchInfoIngestor.api.defaultResponse.league.LeagueResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    private final APISportsIngestor apiSportsIngestor;

    public MainController(APISportsIngestor apiSportsIngestor) {
        this.apiSportsIngestor = apiSportsIngestor;
    }

    @GetMapping("/league")
    public ResponseEntity<LeagueResponse> getLeague() {
        return new ResponseEntity<LeagueResponse>(apiSportsIngestor.requestLeagueResponse(), HttpStatus.OK);
    }

    @GetMapping("/fixture")
    public ResponseEntity<FixtureResponse> getFixture() {
        return new ResponseEntity<FixtureResponse>(apiSportsIngestor.requestFixtureResponse()[0], HttpStatus.OK);
    }
}
