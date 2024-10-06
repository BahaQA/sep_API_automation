@US04
Feature:Retrieve review for a plan resource
  POST/api/v1/plans/review
  Acceptance Criteria:
  In the Request Body;
  1- User should enter all the fields
  2- All the values of the fields should belong to the related customer. (Same with the previous steps)

  In the Response;
  1- Status code should be 200   \
  2- Content-Type should be “application/json”
  3- The value of the paymentType should be the same paymentType of the related priceId (check GET > ProductPrices)


  Scenario Outline: Retrieve review for a plan resource
    Given the request accept type is "application/json"
    When the user sends a POST request to "plans/review" endpoint with the request body in "<JSON file>"
    Then the status code should be 200
    And the Content-type should be "application/json"
    And the value of the "<plan payment type>" should be the same as the product payment type
    Examples:
      | JSON file                                            | plan payment type |
      | src/test/resources/sources/review_plan_resource.json | recurring         |



