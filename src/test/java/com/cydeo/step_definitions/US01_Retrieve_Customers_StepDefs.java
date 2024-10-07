package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class US01_Retrieve_Customers_StepDefs extends APITestBase {


    @When("the user sends a GET request to {string} endpoint with the xApiKey")
    public void the_user_sends_a_get_request_to_endpoint_with_the_xApiKey(String endPoint) {
        String xApiKey = System.getenv("x-api-key");

        response = givenPart.contentType("application/json").header("x-api-key", xApiKey).when().get(endPoint);

        response.prettyPrint();
        thenPart = response.then();
        jp = response.jsonPath();
    }

    @Then("the Content-Type should be {string}")
    public void the_content_type_should_be(String expectedContentType) {
        thenPart.assertThat().header("Content-Type", expectedContentType);
    }

    @Then("the success field should be {string}")
    public void the_success_field_should_be(String successFieldValue) {
        Boolean expectedSuccessFieldValue = Boolean.valueOf(successFieldValue);
        Boolean actualSuccessFieldValue = jp.get("success");
        assertEquals(expectedSuccessFieldValue, actualSuccessFieldValue);
    }
}
