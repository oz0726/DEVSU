package com.devsu.accountOperation.infraestructure.rest;

import com.devsu.accountOperation.infraestructure.vo.ClientVO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
/**
 * REST client for the client/person API
 *
 * @author Olman Ibanez
 */
@Service
public class ClientREST {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${client.api.url}")
    private String API_URL;

    public ClientVO getClient(int client) {
        return restTemplate.getForObject(API_URL +client, ClientVO.class);
    }
}
