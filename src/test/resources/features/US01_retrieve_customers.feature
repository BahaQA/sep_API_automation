  @US01
  Feature:Retrieve the list of all customers
    GET/api/v1/customers
    Acceptance Criteria:
    In the Request;
    1- X-API-Key is required

    In the Response;
    1- Status code should be 200
    2- Content-Type should be “application/json”
    3- success field should be “true”


    Scenario: Retrieve the list of all customers
      Given the request accept type is "application/json"
      When the user sends a GET request to "customers" endpoint with the xApiKey
      And the status code should be 201
      And the Content-Type should be "application/json"
      And the success field should be "true"


