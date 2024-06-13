package com.devsu.clientPerson.features.application.service;
import com.devsu.clientPerson.application.service.ClientService;
import com.devsu.clientPerson.domain.entity.Client;
import com.devsu.clientPerson.domain.entity.Person;
import com.devsu.clientPerson.domain.repository.IClientRepository;
import com.devsu.clientPerson.domain.repository.IPersonRepository;
import com.devsu.clientPerson.infrastructure.vo.ClientVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private IClientRepository clientRepository;

    @Mock
    private IPersonRepository personRepository;

    @InjectMocks
    private ClientService clientService;

    private List<Client> mockClientList;

    @Before
    public void setup() {
        mockClientList = new ArrayList<>();
        Client client1 = new Client();
        Person person1= new Person();
        person1.setName("test name");
        person1.setGenre("test genre");
        person1.setAge("30");
        person1.setAddress("test address");
        person1.setPhoneNumber("55512345");
        person1.setId(12345);
        client1.setPassword("12345");
        client1.setState(true);
        client1.setId(1);
        client1.setPerson(person1);
        mockClientList.add(client1);
        when(clientRepository.findAll()).thenReturn(mockClientList);
    }

    @Test
    public void testGetAllClients() {
        List<ClientVO> result = clientService.getAllClients();
        assertEquals(mockClientList.size(), result.size());
    }
}
