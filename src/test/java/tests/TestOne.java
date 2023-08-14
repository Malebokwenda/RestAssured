package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestOne {
    @Test
    public void test_1(){
        Response response =get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());

    }
    @Test
    public void test_2(){
        baseURI ="https://reqres.in/api";
        given().get("/users?page=2").
                then().
                statusCode(200).
                body("data[1].id",equalTo(8));

    }

}
