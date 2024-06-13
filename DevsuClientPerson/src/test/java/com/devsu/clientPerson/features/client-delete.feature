Feature: Delete clients

  Scenario: Succesfull delete
    * call read("client-post-snippets.feature@Create")
    Given url 'http://localhost:8080'
    And path 'clientes/2'
    When method delete
    Then status 204
