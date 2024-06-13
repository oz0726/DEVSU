Feature: Get all operations

  Background:
    * url 'http://localhost:8081'

  Scenario: Return a list of operations
    Given path 'movimientos'
    When method get
    Then status 200

  Scenario: Return a specific operation
    * call read("operation-post-snippets.feature@Create")
    Given path 'movimientos/1'
    When method get
    Then status 200
    And match $.accountNumber == 1