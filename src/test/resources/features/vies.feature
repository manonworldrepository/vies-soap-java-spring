# src/test/resources/features/vies.feature

Feature: VAT Validation

  Scenario: Validate a valid VAT number
    Given a country code "DE" and a VAT number "123456789"
    When I check the VAT number
    Then the response should be valid
    And the response should contain the country code "DE"
    And the response should contain the VAT number "123456789"