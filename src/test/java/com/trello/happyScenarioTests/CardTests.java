package com.trello.happyScenarioTests;

import com.trello.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CardTests extends BaseTest {

    @Test
    public void createNewCard() {
        // Send a POST request to create a new Card
        Response response = RestAssured.given()
                .baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/cards") // Set the endpoint path for Card creation
                .queryParams(
                        "key", key,          // API key for authentication
                        "token", token,      // Token for user access
                        "name", "My_Card",   // Name of the new Card
                        "idList" , list_id
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
        card_id = response.path("id");

        // Assert that the name of the created board is "My_board"
        Assert.assertEquals(response.path("name"), "My_Card");
    }


    @Test
    public void getCard() {
        // Send a get request to get Card
        Response response = RestAssured.given()
                .baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/cards/"+card_id) // Set the endpoint path for get Card
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
        Assert.assertEquals(response.path("name"), "My_Card");
    }



    @Test
    public void deleteCard() {
        // Send a POST request to delete Card
        Response response = RestAssured.given()
                .baseUri(baseUrl) // Set the base URL of the API (e.g., https://api.trello.com)
                .basePath("/cards/"+card_id) // Set the endpoint path for Card removal
                .queryParams(
                        "key", key,          // API key for authentication
                        "token", token // Token for user access
                )
                .header("Content-Type", "application/json") // Specify content type as JSON
                .when().delete() // Send the delete request
                .then()
                .statusCode(200) // Assert that the response status code is 200 (OK)
                .time(lessThan(3000L)) // Assert that the response time is less than 3 seconds
                .extract().response(); // Extract the response for further assertions and use

        // Print the response body in a readable format
        response.prettyPrint();


    }




}

