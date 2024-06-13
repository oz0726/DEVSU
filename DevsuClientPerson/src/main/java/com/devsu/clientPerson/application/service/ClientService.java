package com.devsu.clientPerson.application.service;

import com.devsu.clientPerson.domain.entity.Client;
import com.devsu.clientPerson.domain.entity.Person;
import com.devsu.clientPerson.domain.repository.IClientRepository;
import com.devsu.clientPerson.domain.repository.IPersonRepository;
import com.devsu.clientPerson.infrastructure.vo.ClientVO;
import com.devsu.clientPerson.util.exception.InfoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Class with business logic of the application
 *
 * @author Olman Ibanez
 */
@Service
public class ClientService {
    @Autowired
    IClientRepository clientRepository;
    @Autowired
    IPersonRepository personRepository;

    public List<ClientVO> getAllClients(){
        List<ClientVO> clients=new ArrayList<ClientVO>();
        List<Client> clientList= clientRepository.findAll();
        clientList.forEach(i -> {
            ClientVO client=new ClientVO();
            client.setAddress(i.getPerson().getAddress());
            client.setAge(i.getPerson().getAge());
            client.setGenre(i.getPerson().getGenre());
            client.setIdNumber(i.getPerson().getId());
            client.setName(i.getPerson().getName());
            client.setPhoneNumber(i.getPerson().getPhoneNumber());
            client.setState(i.getState());
            clients.add(client);
        });
        return clients;
    }
    /**
     * This method queries that a user with the indicated id exists in the PERSON table and then looks for the clients that match
     *
     * @author Olman Ibanez
     */
    public ClientVO getClientByPersonId(Integer id){
        List<Client> clientList= clientRepository.findByPersonId(id);
        Client clientDB= clientList.stream().findFirst().orElseThrow(InfoNotFoundException::new);
        ClientVO client=new ClientVO();
        client.setAddress(clientDB.getPerson().getAddress());
        client.setAge(clientDB.getPerson().getAge());
        client.setGenre(clientDB.getPerson().getGenre());
        client.setIdNumber(clientDB.getPerson().getId());
        client.setName(clientDB.getPerson().getName());
        client.setPhoneNumber(clientDB.getPerson().getPhoneNumber());
        client.setState(clientDB.getState());
        return client;
    }
    /**
     * Logical deletion of the client
     *  @author Olman Ibanez
     */
    public void deleteClient(Integer id){
        List<Client> clientList= clientRepository.findByPersonId(id);
        Client client= clientList.stream().findFirst().orElseThrow(InfoNotFoundException::new);
        client.setState(false);
        clientRepository.save(client);
    }

    public void createClient(ClientVO clientRequest){
        Client client= new Client();
        Person person= new Person();
        person.setAddress(clientRequest.getAddress());
        person.setAge(clientRequest.getAge());
        person.setGenre(clientRequest.getGenre());
        person.setId(clientRequest.getIdNumber());
        person.setName(clientRequest.getName());
        person.setPhoneNumber(clientRequest.getPhoneNumber());
        personRepository.save(person);
        client.setState(true);
        client.setPassword(clientRequest.getPassword());
        client.setPerson(person);
        clientRepository.save(client);
    }

    public void updateClient(ClientVO clientRequest){
        List<Client> clientList= clientRepository.findByPersonId(clientRequest.getIdNumber());
        Client client= clientList.stream().findFirst().orElseThrow(InfoNotFoundException::new);

        Optional.ofNullable(clientRequest.getAddress())
                .filter(address -> !address.trim().isEmpty())
                .ifPresent(client.getPerson()::setAddress);
        Optional.ofNullable(clientRequest.getAge())
                .filter(age -> !age.trim().isEmpty())
                .ifPresent(client.getPerson()::setAge);
        Optional.ofNullable(clientRequest.getGenre())
                .filter(genre -> !genre.trim().isEmpty())
                .ifPresent(client.getPerson()::setGenre);
        Optional.ofNullable(clientRequest.getName())
                .filter(name -> !name.trim().isEmpty())
                .ifPresent(client.getPerson()::setName);
        Optional.ofNullable(clientRequest.getPhoneNumber())
                .filter(phoneNumber -> !phoneNumber.trim().isEmpty())
                .ifPresent(client.getPerson()::setPhoneNumber);
        Optional.ofNullable(clientRequest.getPassword())
                .filter(password -> !password.trim().isEmpty())
                .ifPresent(client::setPassword);
        client.setState(true);

        clientRepository.save(client);
    }
}
