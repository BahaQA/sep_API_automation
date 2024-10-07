package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class US06_Create_Payment_Intent_StepDefs extends APITestBase{

    @Then("the price value should be an Integer value")
    public void the_price_value_should_be_an_integer_value() {
        Object priceValue = jp.getJsonObject("data.price");
        assertTrue( priceValue instanceof Integer);
    }

    @Then("the currency value should be {string}")
    public void the_currency_value_should_be(String expectedCurrencyValue) {
        String actualCurrencyValue = jp.get("data.currency");
        assertEquals(expectedCurrencyValue, actualCurrencyValue);
    }

    @Then("the values of the {string}, {string}, {string}, {string}, and {string} fields should not bu null")
    public void the_values_of_the_and_fields_should_not_bu_null(String paymentIntentIdValue, String paymentSecretValue, String paymentIntentStatusValue, String priceValue, String currencyValue) {
     thenPart.assertThat().body(paymentIntentIdValue, notNullValue());
     thenPart.assertThat().body(paymentSecretValue, notNullValue());
     thenPart.assertThat().body(paymentIntentStatusValue, notNullValue());
     thenPart.assertThat().body(priceValue, notNullValue());
     thenPart.assertThat().body(currencyValue, notNullValue());
    }

}
