Feature: Online Shopping Checkout
  Everybody wants to able to checkout

  Scenario: Checkout product with specific product
    Given User logged in to website with username = "2scholarship4@gmail.com" & password = "Qwerty123!!"
    And User search "SHOES" product
    And User filter product < Rp "10000000"
    And User select first product
    And User select the size of product and add to cart
    When I proceed to checkout
    And I enter my name as ["Akram Faisal" - "Candidate QA"] in the Shipping Address
    And I choose a courier and payment method
    Then I should see a validation for the transaction amount