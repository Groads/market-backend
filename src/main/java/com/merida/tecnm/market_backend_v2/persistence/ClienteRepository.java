package com.merida.tecnm.market_backend_v2.persistence;

import com.merida.tecnm.market_backend_v2.domain.Client;
import com.merida.tecnm.market_backend_v2.domain.repository.ClientRepository;
import com.merida.tecnm.market_backend_v2.persistence.crud.ClienteCrudRepository;
import com.merida.tecnm.market_backend_v2.persistence.entity.Cliente;
import com.merida.tecnm.market_backend_v2.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {

 @Autowired
 private ClienteCrudRepository clienteCrudRepository;

 @Autowired
 private ClientMapper mapper;

 @Override
 public List<Client> getAll() {
  List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
  return mapper.toClients(clientes);
 }

 @Override
 public Optional<Client> getClient(String clientId) {
  return clienteCrudRepository.findById(clientId)
          .map(cliente -> mapper.toClient(cliente));
 }

 @Override
 public Client save(Client client) {
  Cliente cliente = mapper.toCliente(client);
  return mapper.toClient(clienteCrudRepository.save(cliente));
 }

 @Override
 public void delete(String clientId) {
  clienteCrudRepository.deleteById(clientId);
 }
}
