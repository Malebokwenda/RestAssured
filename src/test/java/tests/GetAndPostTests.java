package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetAndPostTests {
    @Test
    public void testGet(){
        baseURI ="https://reqres.in/api";
        given().get("/users?page=2").
                then().
                statusCode(200).
                body("data[4].first_name",equalTo("George"))
                .body("data.first_name", hasItems("George","Rachel"));

    }

    @Test
    public void TestPost(){


        JSONObject request = new JSONObject();
        request.put("name","Faith");
        request.put("job","Software Tester");
        System.out.println(request.toJSONString());

        baseURI ="https://reqres.in/api";
        given().header("ContentType", "application/json").
                accept(ContentType.JSON).
        body(request.toJSONString()).
                when().post("/users").then().
                statusCode(201).
                log().all();
    }
}
