package com.healthx.security.service;

import com.healthx.model.Client;
import com.healthx.repository.ClientRepository;
import com.healthx.security.model.ClientDetailsSecurityWrapper;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaClientDetailsService implements ClientDetailsService {

    private final ClientRepository repository;

    public JpaClientDetailsService(ClientRepository repository) {
        this.repository = repository;
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Optional<Client> client = repository.findByClientId(clientId);
        return client
                .map(ClientDetailsSecurityWrapper::new)
                .orElseThrow(()-> new ClientRegistrationException("Client doesn't exist!"));
    }
}
