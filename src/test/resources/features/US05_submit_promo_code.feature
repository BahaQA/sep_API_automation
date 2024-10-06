@US05
Feature:Submit promo code / Validate in Stripe
  POST/api/v1/plans/promo-code
  Acceptance Criteria:
  In the Request Body;
  1- User should enter all the fields
  2- User should enter the priceId of the related Program
  3- User should enter the productId of the related Program (the one in the qa_test_data, google sheet)
  4- Promo code should be either “off50” or “exp50”

  In the Response;
  1- Status code should be 200
  2- Content-Type should be “application/json”
  3- The fields should be as following:
  Promo Code(in Request)	Promo Code(in Response)	isValid	message
  off50	off50	true	Valid Coupon!
  exp50	exp50	false	Coupon is no longer active.
  4- The value of the price should be an int value
  5- The value of the currency should be “usd”


  Scenario Outline: Submit promo code
    Given the request accept type is "application/json"
    When the user sends a POST request to "plans/promo-code" endpoint with the request body
      | priceId   | price_1OXpPoJmxFuDfdznFNJZKs0I |
      | productId | prod_PMYWvWHeM3LeY9            |
      | promoCode | <promoCode>                    |
    Then the status code should be 200
    And the Content-type should be "application/json"
    And the value of the price should be an Integer value
    And the value of the currency should be "usd"
    And the values of the "<promoCode>", "<isValid>", and "<message>" fields should be as following
    Examples:
      | promoCode | isValid | message                     |
      | off50     | true    | Valid Coupon!               |
      | exp50     | false   | Coupon is no longer active. |

