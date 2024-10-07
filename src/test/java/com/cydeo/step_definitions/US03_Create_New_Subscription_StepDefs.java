package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.datatable.DataTable;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class US03_Create_New_Subscription_StepDefs extends APITestBase{

    @When("the user sends a POST request to {string} endpoint with the request body")
    public void the_user_sends_a_post_request_to_endpoint_with_the_request_body(String endpoint, DataTable dataTable) {

        Map<String, Object> requestBody = dataTable.asMap(String.class, Object.class);

        response = givenPart.contentType("application/json").body(requestBody).when().post(endpoint);
        response.prettyPrint();

        thenPart = response.then();
        jp = response.jsonPath();
    }


    @Then("the Content-type should be {string}")
    public void the_content_type_should_be(String expectedContentType) {
        thenPart.assertThat().contentType(expectedContentType);
    }

    @Then("the values for the subscriptionId, subscriptionKey and priceId shouldn’t be null")
    public void the_values_for_the_subscriptionId_subscriptionKey_and_priceId_shouldn_t_be_null() {
        thenPart.assertThat()
                .body("data.subscriptionId", notNullValue())
                .body("data.subscriptionKey", notNullValue())
                .body("data.priceId", notNullValue())
                .body("success", notNullValue());
    }

    @Then("the subscription should fail with the following error message")
    public void the_subscription_should_fail_with_the_following_error_message(String errorMessage) {
        thenPart.assertThat().body("error.message", equalTo(errorMessage));
    }
}
