package com.cydeo.step_definitions;

import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class US05_Submit_Promo_Code_StepDefs extends APITestBase{

    String promoCode = jp.get("data.promoCode");

    @Then("the value of the price should be an Integer value")
    public void the_value_of_the_price_should_be_an_Integer_value() {
        if (promoCode.equals("exp50")) {
            return;
        }

        Object priceValue = jp.getJsonObject("data.receipt.paymentSummary.basePrice");
        System.out.println("priceValue = " + priceValue);
        assertTrue( priceValue instanceof Integer);
    }

    @Then("the value of the currency should be {string}")
    public void the_value_of_the_currency_should_be(String expectedCurrencyValue) {
        if (promoCode.equals("exp50")) {
            return;
        }
        String actualCurrencyValue = jp.get("data.receipt.paymentSummary.currency");
        assertEquals(expectedCurrencyValue, actualCurrencyValue);
    }

    @Then("the values of the {string}, {string}, and {string} fields should be as following")
    public void the_values_of_the_and_fields_should_be_as_following(String expectedPromoCode, String expectedIsValid, String expectedMessage) {
        String actualPromoCode = jp.get("data.promoCode");
        Boolean actualIsValid = jp.get("data.isValid");
        String actualMessage = jp.get("data.message");
        assertEquals(expectedPromoCode, actualPromoCode);
        assertEquals(Boolean.valueOf(expectedIsValid), actualIsValid);
        assertEquals(expectedMessage, actualMessage);

    }

}
