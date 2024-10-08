package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class US08_Create_New_Purchase_StepDefs extends APITestBase{

    @Then("the field of {string} and {string} should not be null")
    public void the_field_of_and_should_not_be_null(String customerIdPath, String parentIdPath) {
        String customerId = jp.getString(customerIdPath);
        Integer parentId = jp.getInt(parentIdPath);

        assertThat(customerId, notNullValue());
        assertThat(parentId, notNullValue());
    }

    @Then("the values of the {string}, {string}, and {string} should match the request body")
    public void the_values_of_the_and_should_match_the_request_body(String firstNameInRequest, String lastNameInRequest, String emailInRequest) {
        String firstNameInResponse = jp.getString("data.customer.firstName");
        String lastNameInResponse = jp.getString("data.customer.lastName");
        String emailInResponse = jp.getString("data.customer.email");

        assertThat(firstNameInRequest, equalTo(firstNameInResponse));
        assertThat(lastNameInRequest, equalTo(lastNameInResponse));
        assertThat(emailInRequest, equalTo(emailInResponse));
    }

}
