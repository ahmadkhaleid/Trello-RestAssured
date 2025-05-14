package com.trello.happyScenarioTests;

import com.trello.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BoardTests extends BaseTest {

    @Test
    public void createNewBoard() {
        // Send a POST request to create a new Trello board
        Response response = RestAssured.given().baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/boards") // Set the endpoint path for board creation
                .queryParams("key", key,          // API key for authentication
                        "token", token,      // Token for user access
                        "name", "My_board",  // Name of the new board
                        "defaultLists", "false" // Avoid creating default lists (To Do, Doing, Done)
                ).header("Content-Type", "application/json") // Specify content type as JSON
                .when().post() // Send the POST request
                .then().statusCode(200) // Assert that the response status code is 200 (OK)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();

        // Extract the ID of the created board and assign it to a class-level variable
        board_id = response.path("id");

        // Assert that the name of the created board is "My_board"
        Assert.assertEquals(response.path("name"), "My_board");
    }


    @Test
    public void getBoard() {
        // Send a POST request to create a new Trello board
        Response response = RestAssured.given().baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/boards/" + board_id) // Set the endpoint path for board creation
                .queryParams("key", key,          // API key for authentication
                        "token", token // Token for user access
                ).header("Content-Type", "application/json") // Specify content type as JSON
                .when().get() // Send the POST request
                .then().statusCode(200) // Assert that the response status code is 200 (OK)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();


        // Assert that the name of the created board is "My_board"
        Assert.assertEquals(response.path("name"), "My_board");
    }


    @Test(dependsOnMethods = {"com.trello.happyScenarioTests.CardTests.deleteCard"})
    public void deleteBoard() {
        // Send a POST request to create a new Trello board
        Response response = RestAssured.given().baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/boards/" + board_id) // Set the endpoint path for board creation
                .queryParams("key", key,          // API key for authentication
                        "token", token // Token for user access
                ).header("Content-Type", "application/json") // Specify content type as JSON
                .when().delete() // Send the POST request
                .then().statusCode(200) // Assert that the response status code is 200 (OK)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();


    }


}
