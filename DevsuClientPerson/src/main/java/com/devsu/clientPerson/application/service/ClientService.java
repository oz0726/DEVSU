package com.devsu.clientPerson.application.service;

import com.devsu.clientPerson.domain.entity.Client;
import com.devsu.clientPerson.domain.repository.IClientRepository;
import com.devsu.clientPerson.infrastructure.vo.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            client.setState(i.getState()==1 ? "Active" : "Inactive");
            clients.add(client);
        });
        return clients;
    }
}
