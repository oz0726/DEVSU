Feature: Create clients

  Scenario: Succesfull creation
    Given url 'http://localhost:8080'
    And path 'clientes'
    And request {"name": "test","genre": "test","age": "99","address": "test address","phoneNumber": "55512345","password": "12345","idNumber": 12345}
    When method post
    Then status 201
