@US08
Feature:Create a new purchase resource
  POST/api/v1/purchases
  Acceptance Criteria:
  In the Request Body;
  1- The fields of First Name, Last Name, email and Phone Number shouldn’t be null
  2- User should enter a proper email
  3- lmsProgramCode, lmsProgramId, lmsProgramName shouldn’t be null

  In the Response;
  1- Status code should be 200
  2- Content-Type should be “application/json”
  3- The field of customerId shouldn’t be null
  4- The fields of First Name, Last Name and email should match with the Request body
  5- The field of parentId shouldn’t be null


  Scenario Template: Create a new purchase resource
    Given the request accept type is "application/json"
    When the user sends a POST request to "purchases" endpoint with the request body in "<JSON file>"
    Then the status code should be 200
    And the Content-type should be "application/json"
    And the field of "<customerId>" and "<parentId>" should not be null
    And the values of the "<first name>", "<last name>", and "<email>" should match the request body
    Examples:
      | JSON file                                           | first name | last name | email            | customerId               | parentId                     |
      | src/test/resources/sources/create_new_purchase.json | Bahir      | Kaptan    | kaptan@gmail.com | data.customer.customerId | data.customer.parent.parentId |
