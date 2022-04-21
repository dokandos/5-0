package com.MatchInfoIngestor.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;


//TODO finish
public class APISportsIngestorTests {

    @Autowired
    private APISportsIngestor apiSportsIngestor;

    @Test
    void getCorrectClassType(){
        class Testclass{
            private String field1, field2, field3;

            Testclass(String field1, String field2, String field3){
                this.field1=field1;
                this.field2=field2;
                this.field3=field3;
            }
        }

        Testclass testclass = new Testclass("a", "b", "c");

        JsonNode node = (new ObjectMapper()).valueToTree(testclass);

        assertEquals(
                apiSportsIngestor.getResponse(node, Testclass.class),
                testclass
            );
    }

}
