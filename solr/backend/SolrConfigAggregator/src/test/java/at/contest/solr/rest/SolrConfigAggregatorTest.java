package at.contest.solr.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SolrConfigAggregatorTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/SolrConfigAggregator")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}