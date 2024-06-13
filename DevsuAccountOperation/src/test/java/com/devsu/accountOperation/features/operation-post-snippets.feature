@Ignore
Feature: Reusable Creation of operations

  @Create
  Scenario: Succesfull test creation
    Given url 'http://localhost:8081'
    And path 'movimientos'
    And request {"operationId": 2, "operationDate": "2022-02-10T00:00:00.000+00:00","operationType": "Deposito","operationValue": 400,"newBalance": 401,"accountNumber": 1}
    When method post
    Then status 201