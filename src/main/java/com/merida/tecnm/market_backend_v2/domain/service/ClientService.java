package com.merida.tecnm.market_backend_v2.domain.service;

import com.merida.tecnm.market_backend_v2.domain.Client;
import com.merida.tecnm.market_backend_v2.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

 @Autowired
 private ClientRepository clientRepository;
 @Autowired
 private PasswordEncoder passwordEncoder;

 public List<Client> getAll() {
  return clientRepository.getAll();
 }

 public Optional<Client> getClient(String clientId) {
  return clientRepository.getClient(clientId);
 }

 public Client save(Client client) {
  if (client.getPassword() != null && !client.getPassword().isBlank()) {
   client.setPassword(passwordEncoder.encode(client.getPassword()));
  }
  return clientRepository.save(client);
 }

 public Optional<Client> update(String clientId, Client client) {
  return getClient(clientId).map(existingClient -> {
   client.setClientId(clientId);
   if (client.getPassword() != null && !client.getPassword().isBlank()) {
    client.setPassword(passwordEncoder.encode(client.getPassword()));
   } else {
    client.setPassword(existingClient.getPassword());
   }
   return clientRepository.save(client);
  });
 }

 public boolean delete(String clientId) {
  if (getClient(clientId).isPresent()) {
   clientRepository.delete(clientId);
   return true;
  } else {
   return false;
  }
 }
}