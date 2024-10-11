package com.cydeo.step_definitions;

import com.cydeo.pojo.ConfirmPurchase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import lombok.Data;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Data
public class US09_Confirm_Purchase_StepDefs extends APITestBase{
    String purchaseName;
    String purchaseLastName;
    String purchaseEmail;
    String purchaseProduct;

    @Given("the request payload has the same {string}, {string}, {string}, and {string} as the {string}")
    public void the_request_payload_has_the_same_and_as_the(String firstName, String lastName, String email, String product, String purchasesPayload) {
        File requestBodyFile = new File(purchasesPayload);
        JsonPath requestBodyPath = new JsonPath(requestBodyFile);

        purchaseName = requestBodyPath.getString(firstName);
        purchaseLastName = requestBodyPath.getString(lastName);
        purchaseEmail = requestBodyPath.getString(email);
        purchaseProduct = requestBodyPath.getString(product);

        ConfirmPurchase confirmPurchase = new ConfirmPurchase();
        confirmPurchase.setName(purchaseName);
        confirmPurchase.setProduct(purchaseProduct);
        confirmPurchase.setEmail(purchaseEmail);

        givenPart = givenPart.contentType(ContentType.JSON).body(confirmPurchase);
    }


    @When("the user sends a POST request to {string} endpoint")
    public void the_user_sends_a_post_request_to_endpoint(String endpoint) {
        response = givenPart.when().post(endpoint);
        thenPart = response.then();
        jp = response.jsonPath();
    }

    @Then("the values of the {string}, {string}, and {string} should match the customer purchase information")
    public void the_values_of_the_and_should_match_the_customer_purchase_information(String name, String lastName, String email) {
        String nameValue = jp.getString(name);
        String lastNameValue = jp.getString(lastName);
        String emailValue = jp.getString(email);

        assertThat(nameValue, equalTo(purchaseName));
        assertThat(lastNameValue, equalTo(purchaseLastName));
        assertThat(emailValue, equalTo(purchaseEmail));

    }

}
