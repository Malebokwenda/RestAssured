package tests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class APITesting {
    @Test
    public void createUserTest() {
        JSONObject request = new JSONObject();
        request.put("name", "Faith");
        request.put("job", "Software Tester");
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        ValidatableResponse postResponse = given().header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when().post("/users")
                .then()
                .statusCode(201)
                .log().all();

        // Validate user information in the response
        String userId = postResponse.extract().path("id");
        String userName = postResponse.extract().path("name");
        String userJob = postResponse.extract().path("job");

        Assert.assertEquals(userName, "Faith");
        Assert.assertEquals(userJob, "Software Tester");
    }
}
