@US06
Feature:
  Create a new payment intent resource in Stripe

  POST/api/v1/plans/payment-intent
  Acceptance Criteria:
  In the Request Body;
  1- User should enter all the fields
  2- customerId should be the id of a registered customer
  3- User should enter the productId of the related Program (check GET > ProductInfo)
  4- priceId should be the one of which payment type is one_time (check GET > ProductPrices)
  5- Payment method should be “card”

  In the Response;
  1- Status code should be 200
  2- Content-Type should be “application/json”
  3- The fields of paymentIntentId, paymentSecret, PaymentIntentStatus, price and currency shouldn’t be null
  4- The value of the price should be an int value
  5- The value of the currency should be “usd”


  Scenario Outline: Create a new payment intent
    Given the request accept type is "application/json"
    When the user sends a POST request to "plans/payment-intent" endpoint with the request body
      | customerId    | <customerId>                  |
      | productId     | <productId>                   |
      | priceId       | price_1OXpPoJmxFuDfdznFNJZKs0I |
      | paymentMethod | card                          |
    Then the status code should be 200
    And the Content-type should be "application/json"
    And the price value should be an Integer value
    And the currency value should be "usd"
    And the values of the "data.paymentIntentId", "data.paymentSecret", "data.paymentIntentStatus", "data.price", and "data.currency" fields should not bu null
    Examples:
      | productId | customerId |
      | 1         | 37         |
      | 2         | 88         |
      | 3         | 107        |
      | 4         | 318        |