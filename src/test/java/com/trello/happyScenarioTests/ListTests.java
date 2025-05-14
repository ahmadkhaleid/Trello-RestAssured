package com.trello.happyScenarioTests;

import com.trello.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ListTests extends BaseTest {

    @Test
    public void createNewList() {
        // Send a POST request to create a new Trello board
        Response response = RestAssured.given()
                .baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/lists") // Set the endpoint path for list creation
                .queryParams(
                        "key", key,          // API key for authentication
                        "token", token,      // Token for user access
                        "name", "My_List",   // Name of the new board
                        "idBoard",board_id

                )
                .header("Content-Type", "application/json") // Specify content type as JSON
                .when().post() // Send the POST request
                .then()
                .statusCode(200) // Assert that the response status code is 200 (OK)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();

        // Extract the ID of the created board and assign it to a class-level variable
        list_id = response.path("id");

        // Assert that the name of the created board is "My_board"
        Assert.assertEquals(response.path("name"), "My_List");
    }


    @Test
    public void getList() {
        // Send a POST request to create a new Trello board
        Response response = RestAssured.given()
                .baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/lists/"+list_id) // Set the endpoint path for get list
                .queryParams(
                        "key", key,          // API key for authentication
                        "token", token // Token for user access
                )
                .header("Content-Type", "application/json") // Specify content type as JSON
                .when().get() // Send the get request
                .then()
                .statusCode(200) // Assert that the response status code is 200 (OK)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();


        // Assert that the name of the created board is "My_board"
        Assert.assertEquals(response.path("name"), "My_List");
    }


    @Test
    public void updateList() {
        // Send a POST request to create a new Trello board
        Response response = RestAssured.given()
                .baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/lists/"+list_id) // Set the endpoint path for updated list
                .queryParams(
                        "key", key,          // API key for authentication
                        "token", token,      // Token for user access
                        "name", "updated_name"   // Name of the new board
                )
                .header("Content-Type", "application/json") // Specify content type as JSON
                .when().put() // Send the put request
                .then()
                .statusCode(200) // Assert that the response status code is 200 (OK)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();

        // Extract the ID of the created board and assign it to a class-level variable
        list_id = response.path("id");

        // Assert that the name of the created board is "My_board"
        Assert.assertEquals(response.path("name"), "updated_name");
    }



}

