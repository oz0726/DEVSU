package com.devsu.clientPerson.application.service;

import com.devsu.clientPerson.domain.entity.Client;
import com.devsu.clientPerson.domain.entity.Person;
import com.devsu.clientPerson.domain.repository.IClientRepository;
import com.devsu.clientPerson.infrastructure.vo.ClientRequest;
import com.devsu.clientPerson.infrastructure.vo.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    IClientRepository clientRepository;

    public List<ClientResponse> getAllClients(){
        List<ClientResponse> clients=new ArrayList<ClientResponse>();
        List<Client> clientList= clientRepository.findAll();
        clientList.forEach(i -> {
            ClientResponse client=new ClientResponse();
            client.setAddress(i.getPerson().getAddress());
            client.setAge(i.getPerson().getAge());
            client.setGenre(i.getPerson().getGenre());
            client.setIdNumber(i.getPerson().getId());
            client.setName(i.getPerson().getName());
            client.setPhoneNumber(i.getPerson().getPhoneNumber());
            client.setState(i.getState()==1);
            clients.add(client);
        });
        return clients;
    }

    public ClientResponse getClientByPersonId(Integer id){
        List<Client> clientList= clientRepository.findByPersonId(id);
        Client clientDB= clientList.stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Error obteniendo información"));
        ClientResponse client=new ClientResponse();
        client.setAddress(clientDB.getPerson().getAddress());
        client.setAge(clientDB.getPerson().getAge());
        client.setGenre(clientDB.getPerson().getGenre());
        client.setIdNumber(clientDB.getPerson().getId());
        client.setName(clientDB.getPerson().getName());
        client.setPhoneNumber(clientDB.getPerson().getPhoneNumber());
        client.setState(clientDB.getState()==1);
        return client;
    }

    public void deleteClient(Integer id){
        List<Client> clientList= clientRepository.findByPersonId(id);
        Client client= clientList.stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Error obteniendo información"));
        client.setState(0);
        clientRepository.save(client);
    }

    public void createClient(ClientRequest clientRequest){
        Client client= new Client();
        Person person= new Person();
        person.setAddress(clientRequest.getAddress());
        person.setAge(clientRequest.getAge());
        person.setGenre(clientRequest.getGenre());
        person.setId(clientRequest.getIdNumber());
        person.setName(clientRequest.getName());
        person.setPhoneNumber(clientRequest.getPhoneNumber());
        client.setState(clientRequest.isState()? 1 : 0);
        client.setPassword(clientRequest.getPassword());
        client.setPerson(person);
        clientRepository.save(client);
    }

    public void updateClient(ClientRequest clientRequest){
        List<Client> clientList= clientRepository.findByPersonId(clientRequest.getIdNumber());
        Client client= clientList.stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Error obteniendo información"));
        Person newPerson= new Person();

        Optional.ofNullable(clientRequest.getAddress())
                .filter(address -> !address.trim().isEmpty())
                .ifPresent(newPerson::setAddress);
        Optional.ofNullable(clientRequest.getAge())
                .filter(age -> !age.trim().isEmpty())
                .ifPresent(newPerson::setAge);
        Optional.ofNullable(clientRequest.getGenre())
                .filter(genre -> !genre.trim().isEmpty())
                .ifPresent(newPerson::setGenre);
        Optional.ofNullable(clientRequest.getName())
                .filter(name -> !name.trim().isEmpty())
                .ifPresent(newPerson::setName);
        Optional.ofNullable(clientRequest.getPhoneNumber())
                .filter(phoneNumber -> !phoneNumber.trim().isEmpty())
                .ifPresent(newPerson::setPhoneNumber);
        Optional.ofNullable(clientRequest.getPassword())
                .filter(password -> !password.trim().isEmpty())
                .ifPresent(client::setPassword);

        client.setState(clientRequest.isState()? 1 : 0);
        client.setPerson(newPerson);

        clientRepository.save(client);
    }
}
