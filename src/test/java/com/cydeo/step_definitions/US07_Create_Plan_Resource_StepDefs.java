package com.cydeo.step_definitions;

import com.cydeo.pojo.CreatePlanResource;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class US07_Create_Plan_Resource_StepDefs extends APITestBase{

    @When("the user sends a POST request to {string} endpoint with the request payload")
    public void the_user_sends_a_post_request_to_endpoint_with_the_request_payload(String endpoint, DataTable dataTable) {

        Map<String, Object> requestBody = dataTable.asMap(String.class, Object.class);
        Integer customerId = Integer.parseInt(requestBody.get("customerId").toString());
        Integer programId = Integer.parseInt(requestBody.get("programId").toString());
        String priceId = (String) requestBody.get("priceId");
        String paymentMethod = (String) requestBody.get("paymentMethod");

        CreatePlanResource createPlanResource = new CreatePlanResource();
        createPlanResource.setCustomerId(customerId);
        createPlanResource.setProgramId(programId);
        createPlanResource.setPriceId(priceId);
        createPlanResource.setPaymentMethod(paymentMethod);

        response = givenPart.contentType("application/json").body(createPlanResource).when().post(endpoint);
        response.prettyPrint();

        thenPart = response.then();
        jp = response.jsonPath();
    }

    @Then("the values of the {string}, {string}, {string}, and {string} fields should not bu null")
    public void the_values_of_the_and_fields_should_not_bu_null(String paymentId, String paymentKey, String priceId, String type) {
        assertThat(paymentId, notNullValue());
        assertThat(paymentKey, notNullValue());
        assertThat(priceId, notNullValue());
        assertThat(type, notNullValue());
    }


    @Then("the value of the {string} should be the same as the {string}")
    public void the_value_of_the_should_be_the_same_as_the(String priceIdInResponse, String priceIdInRequest) {
        priceIdInResponse = jp.getString(priceIdInResponse);
        assertThat(priceIdInRequest, equalTo(priceIdInResponse));
    }


}
