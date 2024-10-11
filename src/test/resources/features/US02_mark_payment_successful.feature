@US02
Feature: User can mark payment as successful
  POST/api/v1/plans/success
  Acceptance Criteria:
  In the Request Body;
  1- User should enter all the fields
  2- customerId should belong to a registered customer
  3- The values of the paymentId,  priceId should belong to that customer (check POST > Plans)
  4- paymentMethod should be “card”
  5- isSuccessful should be “true”

  In the Response;
  1- Status code should be 204 No Content

  Scenario: Mark payment as successful
    Given the request accept type is "application/json"
    When the user sends a POST request to "/plans/success" endpoint with the following request body
      """
    {
      "paymentType": "recurring",
      "paymentId": "pi_3PwrZsJmxFuDfdzn0umZySlv",
      "priceId": "price_1OXpPoJmxFuDfdznFNJZKs0I",
      "customerId": 262,
      "paymentMethod": "card",
      "isSuccessful": true
    }
    """
    Then the status code should be 204



















    #And the response content type should be "application/json"
    #And the "paymentId" field value should be "<paymentID>"
    #And the "priceId" field value should be "<priceID>"
    #And the "paymentMethod" field value should be "<payment method>"
    #And the "isSuccessful" field value should be "true"

   # Examples:
    #  | customerId  | paymentID | priceID | payment method                   |
     # | paymentID | 7706         | 650        | Test Automation with Selenium   |


