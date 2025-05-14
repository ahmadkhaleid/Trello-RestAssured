package com.trello.badScenarioTests;

import com.trello.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class BoardBadTests extends BaseTest {

    @Test
    public void CreateBoardWithMissingName() {
        // Send a POST request to create a new Trello board
        Response response = RestAssured.given().baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/boards") // Set the endpoint path for board creation
                .queryParams("key", key,          // API key for authentication
                        "token", token,      // Token for user access
                        "defaultLists", "false" // Avoid creating default lists (To Do, Doing, Done)
                ).header("Content-Type", "application/json") // Specify content type as JSON
                .when().post() // Send the POST request
                .then().statusCode(400) // Assert that the response status code is 400 (bad request)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();


        // Assert that the name of the created board is "My_board"
        Assert.assertEquals(response.path("message"), "invalid value for name");
    }


    @Test
    public void CreateBoardWithInvalidToken() {
        // Send a Wrong POST request to create a new Trello board
        Response response = RestAssured.given().baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/boards") // Set the endpoint path for board creation
                .queryParams("key", key,          // API key for authentication
                        "token", "ATTAe71b526932877059390ba6396ab6313f00aff99a11984655ec4052993603f33873FC7A0D",      // Wrong Token
                        "defaultLists", "false" // Avoid creating default lists (To Do, Doing, Done)
                ).header("Content-Type", "application/json") // Specify content type as JSON
                .when().post() // Send the POST request
                .then().statusCode(401) // Assert that the response status code is 400 (Unauthorized)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();
    }


    @Test
    public void getBoardWithInvalidId() {
        // Send a wrong get request to get Trello board
        Response response = RestAssured.given().baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/boards/68079d3383ff2e0cac7fa505") // Set the endpoint path for get board
                .queryParams("key", key,          // API key for authentication
                        "token", token // Token for user access
                ).header("Content-Type", "application/json") // Specify content type as JSON
                .when().get() // Send the POST request
                .then().statusCode(404) // Assert that the response status code is 404 (Not Found)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();

    }




}
