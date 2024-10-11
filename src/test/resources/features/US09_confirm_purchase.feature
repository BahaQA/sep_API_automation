@US09
Feature:
  Acceptance Criteria:
  In the Request Body;
  1- The fields of name, email and Product shouldn’t be null and should be the same with the ALSEP2-356
  2- User should enter a proper email
  3- User should enter the same Program name with the ALSEP2-356

  In the Response;
  1- Status code should be 200
  2- Content-Type should be “application/json”
  3- The field of customerId shouldn’t be null
  3- The fields of First Name, Last Name and email should match with the related customer personal information
  4- The field of parentId shouldn’t be null


  Scenario Template: Confirm purchase
    Given the request accept type is "application/json"
    And the request payload has the same "customer.firstName", "customer.lastName", "customer.email", and "product.lmsProgramName" as the "<request payload of the purchases endpoint>"
    When the user sends a POST request to "purchases/confirm" endpoint
    Then the status code should be 200
    And the Content-type should be "application/json"
    And the field of "<customerId>" and "<parentId>" should not be null
    And the values of the "<firstName>", "<lastName>", and "<email>" should match the customer purchase information
    Examples:
      | firstName               | lastName               | email               | customerId               | parentId                      | request payload of the purchases endpoint           |
      | data.customer.firstName | data.customer.lastName | data.customer.email | data.customer.customerId | data.customer.parent.parentId | src/test/resources/sources/create_new_purchase.json |
