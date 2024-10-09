@US07
Feature: Create a new plan resource
  POST/api/v1/plans
  Acceptance Criteria:
  In the Request Body;
  1- User should enter all the fields
  2- customerId should be the id of a registered customer
  3- User should enter the same programId of the related customer
  4- User should enter one of the priceIds of the related Product Price (GET > ProductPrices)
  5- Payment method should be “card”

  In the Response;
  1- Status code should be 200
  2- Content-Type should be “application/json”
  3- The fields of paymentId, paymentKey, priceId and type shouldn’t be null
  4- the value of the priceId should be the same with the one in the Request Body
  {
  "customerId": 262,
  "programId": 56,
  "priceId": "price_1OXpPoJmxFuDfdznFNJZKs0I",
  "paymentMethod": "card"
  }


  Scenario Template: Create a new plan resource
    Given the request accept type is "application/json"
    When the user sends a POST request to "plans" endpoint with the request payload
      | customerId    | <customerId>    |
      | programId     | <programId>     |
      | priceId       | <priceId>       |
      | paymentMethod | <paymentMethod> |
    Then the status code should be 200
    And the Content-type should be "application/json"
    And the values of the "data.paymentId", "data.paymentKey", "data.priceId", and "data.type" fields should not be null
    And the value of the "data.priceId" should be the same as the "<priceId>" in the request
    Examples:
      | programId | customerId | priceId                        | paymentMethod |
      | 4         | 145        | price_1OXpPoJmxFuDfdznFNJZKs0I | card          |
      | 5         | 387        | price_1OXpPoJmxFuDfdznRZujBIls | card          |
      | 11        | 200        | price_1OXpPoJmxFuDfdznFNJZKs0I | card          |
      | 56        | 601        | price_1OXpPoJmxFuDfdznRZujBIls | card          |





