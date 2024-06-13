Feature: Get all clients

  Scenario: Return a list of clients
    Given url 'http://localhost:8080'
    And path 'clientes'
    When method get
    Then status 200
