package com.cydeo.step_definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class US02_Mark_Payment_Successful_StepDefs extends APITestBase{

    @Given("the request accept type is {string}")
    public void the_request_accept_type_is(String acceptHeader) {
        givenPart.accept(acceptHeader);
    }

    @When("the user sends a POST request to {string} endpoint with the following request body")
    public void the_user_sends_a_post_request_to_endpoint_with_the_following_request_body(String endPoint, String requestBody) {
        response = givenPart.contentType("application/json").body(requestBody).when().post(endPoint);
        thenPart = response.then();
        jp = response.jsonPath();
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer statusCode) {
        thenPart.statusCode(statusCode);
    }
}