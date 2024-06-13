Feature: Update clients

  Scenario: Succesfull update
    * call read("client-post-snippets.feature@Create")
    Given url 'http://localhost:8080'
    And path 'clientes'
    And request {"name": "test new","genre": "test new","address": "test address new","idNumber": 2}
    When method patch
    Then status 200
