Feature: Get all clients

  Background:
    * url 'http://localhost:8080'

  Scenario: Return a list of clients
    Given path 'clientes'
    When method get
    Then status 200

  Scenario: Return a specific client
    * call read("client-post-snippets.feature@Create")
    Given path 'clientes/2'
    When method get
    Then status 200
    And match $.name == 'test'