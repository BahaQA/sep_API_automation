package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class US08_Create_New_Purchase_StepDefs extends APITestBase{

    String planPriceID = "";
    String firstNameInResponse = "";
    String lastNameInResponse = "";
    String emailInResponse = "";

    @When("the user sends a POST request to {string} endpoint with the request body in a {string}")
    public void the_user_sends_a_post_request_to_endpoint_with_the_request_body_in_a(String endPoint, String requestBody) {
        File requestBodyFile = new File(requestBody);
        JsonPath requestBodyPath = new JsonPath(requestBodyFile);
        planPriceID = requestBodyPath.getString("priceId");

        response = givenPart.contentType(ContentType.JSON).body(requestBodyFile).when().post(endPoint);
        thenPart = response.then();
        response.prettyPrint();

        jp = response.jsonPath();
    }

    @Then("the field of {string} and {string} should not be null")
    public void the_field_of_and_should_not_be_null(String customerIdPath, String parentIdPath) {
        String customerId = jp.getString(customerIdPath);
        Integer parentId = jp.getInt(parentIdPath);

        assertThat(customerId, notNullValue());
        assertThat(parentId, notNullValue());
    }

    @Then("the values of the {string}, {string}, and {string} should match the request body")
    public void the_values_of_the_and_should_match_the_request_body(String firstNameInRequest, String lastNameInRequest, String emailInRequest) {
        firstNameInResponse = jp.getString("data.customer.firstName");
        lastNameInResponse = jp.getString("data.customer.lastName");
        emailInResponse = jp.getString("data.customer.email");

        assertThat(firstNameInRequest, equalTo(firstNameInResponse));
        assertThat(lastNameInRequest, equalTo(lastNameInResponse));
        assertThat(emailInRequest, equalTo(emailInResponse));
    }

}
