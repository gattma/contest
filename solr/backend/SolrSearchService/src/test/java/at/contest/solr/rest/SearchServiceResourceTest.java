package at.contest.solr.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SearchServiceResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/SolrSearchService?text=Dreiseitl")
          .then()
             .statusCode(200);
    }

}