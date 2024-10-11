@US03
Feature:Create a new subscription resource in Stripe
  POST/api/v1/plans/subscription
  Acceptance Criteria:
  In the Request Body;
  1- User should enter all the fields
  2- customerId should belong to a registered customer
  3- The value of the priceId should be one of the recurring type prices (check GET > ProductPrices)
  4- paymentMethod should be “card”
  5- promoCode should be “off50”

  In the Response;
  1- Status code should be 200
  2- Content-Type should be “application/json”
  3- The value of the subscriptionId, subscriptionKey and priceId shouldn’t be null

  5- In the Request Body, if the priceId is one of the one_time prices (check GET > ProductPrices),
  Status code should be 409
  message should be “Subscriptions cannot be created for one_time prices.
  6- In the Request Body, if the promoCode is “exp50”
  Status code should be 409
  message should be “ Promotion code is no longer active”


  Scenario: Create a new subscription resource in Stripe
    Given the request accept type is "application/json"
    When the user sends a POST request to "/plans/subscription" endpoint with the request body
      | customerId    | 5                              |
      | priceId       | price_1OXpPoJmxFuDfdznRZujBIls |
      | paymentMethod | card                           |
      | promoCode     | off50                          |
    Then the status code should be 200
    And the Content-type should be "application/json"
    And the values for the subscriptionId, subscriptionKey and priceId shouldn’t be null


  Scenario: Create a new subscription resource with a one-time price
    Given the request accept type is "application/json"
    When the user sends a POST request to "plans/subscription" endpoint with the request body
      | customerId    | 262                            |
      | priceId       | price_1OXpPoJmxFuDfdznFNJZKs0I |
      | paymentMethod | card                           |
      | promoCode     | off50                          |
    Then the status code should be 409
    And the Content-type should be "application/json"
    And the subscription should fail with the following error message
    """
    Subscriptions cannot be created for one_time prices.
    """


  Scenario: Create a new subscription resource with the promoCode exp50
    Given the request accept type is "application/json"
    When the user sends a POST request to "plans/subscription" endpoint with the request body
      | customerId    | 262                            |
      | priceId       | price_1OXpPoJmxFuDfdznRZujBIls |
      | paymentMethod | card                           |
      | promoCode     | exp50                          |
    Then the status code should be 409
    And the Content-type should be "application/json"
    And the subscription should fail with the following error message
    """
    Promotion code is no longer active.
    """



