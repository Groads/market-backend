package com.merida.tecnm.market_backend_v2.domain.repository;

import com.merida.tecnm.market_backend_v2.domain.Client;
import java.util.*;
public interface ClientRepository {
 List<Client> getAll();
 Optional<Client> getClient(String id);
 Client save(Client v);
 void delete(String id);
}
