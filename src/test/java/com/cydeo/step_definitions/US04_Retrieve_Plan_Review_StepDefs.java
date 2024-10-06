package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class US04_Retrieve_Plan_Review_StepDefs extends APITestBase{
    String planPriceID = "";

    @When("the user sends a POST request to {string} endpoint with the request body in {string}")
    public void the_user_sends_a_post_request_to_endpoint_with_the_request_body_in(String endPoint, String requestBody) throws InterruptedException {
        File requestBodyFile = new File(requestBody);
        JsonPath requestBodyPath = new JsonPath(requestBodyFile);
        planPriceID = requestBodyPath.getString("priceId");
        System.out.println("planPriceID = " + planPriceID);

        response = givenPart.contentType(ContentType.JSON).body(requestBodyFile).when().post(endPoint);
        thenPart = response.then();
        response.prettyPrint();

        jp = response.jsonPath();
    }

    @Then("the value of the {string} should be the same as the product payment type")
    public void the_value_of_the_should_be_the_same_as_the_product_payment_type(String planPaymentType) {
        String productPaymentType = "";

       response = givenPart.contentType(ContentType.JSON).when().get("products/56/prices");
       jp = response.jsonPath();
        List<Map<String, Object>> prices = jp.getList("data.prices");

        for (Map<String, Object> price : prices) {
            String priceId = price.get("priceId").toString();
            if (priceId.equals(planPriceID)) {
                productPaymentType = ((Map<String, Object>) price.get("receipt")).get("paymentType").toString();
                break;
            }
        }
        System.out.println("productPaymentType = " + productPaymentType);
        System.out.println("planPaymentType = " + planPaymentType);

        assertEquals(planPaymentType, productPaymentType);

    }

}
