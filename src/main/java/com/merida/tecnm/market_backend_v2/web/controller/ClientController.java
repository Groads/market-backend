package com.merida.tecnm.market_backend_v2.web.controller;

import com.merida.tecnm.market_backend_v2.domain.Client;
import com.merida.tecnm.market_backend_v2.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "Manage your clients")
public class ClientController {

 @Autowired
 private ClientService clientService;

 @GetMapping("")
 @Operation(summary = "Get all clients",
         description = "Return a list of all registered clients")
 @ApiResponse(responseCode = "200",
         description = "Successful retrieval of clients")
 @ApiResponse(responseCode = "500",
         description = "Internal Server Error")
 public ResponseEntity<List<Client>> getAll() {
  return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
 }

 @GetMapping("/{id}")
 @Operation(summary = "Get client by ID",
         description = "Return a client by its ID if it exists")
 @ApiResponse(responseCode = "200",
         description = "Client Found")
 @ApiResponse(responseCode = "404",
         description = "Client Not Found")
 @ApiResponse(responseCode = "500",
         description = "Internal Server Error")
 public ResponseEntity<Client> getClient(
         @Parameter(description = "ID of the client to be retrieved",
                 example = "2552243",
                 required = true)
         @PathVariable("id") String clientId) {
  return clientService.getClient(clientId)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
 }

 @PostMapping("")
 @Operation(
         summary = "Create a new client",
         description = "Register a new client and return it",
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                 required = true,
                 content = @Content(
                         examples = @ExampleObject(
                                 name = "Example Client",
                                 value =
                                         """
                                         {
                                            "clientId": "1001",
                                            "firstName": "Juan",
                                            "lastName": "Pérez",
                                            "cellPhone": 9991234567,
                                            "address": "Calle 60 # 123",
                                            "email": "juan.perez@correo.com"
                                         }
                                         """
                         )
                 )
         )
 )
 @ApiResponse(responseCode = "201", description = "Client created successfully")
 @ApiResponse(responseCode = "400", description = "Invalid client data")
 @ApiResponse(responseCode = "401", description = "Unauthorized")
 @ApiResponse(responseCode = "403", description = "Forbidden")
 @ApiResponse(responseCode = "409", description = "Client conflict")
 @ApiResponse(responseCode = "500", description = "Internal server error")
 public ResponseEntity<Client> save(@RequestBody Client client) {
  return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 @Operation(
         summary = "Update an existing client",
         description = "Updates a client by its ID and returns it",
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                 required = true,
                 content = @Content(
                         examples = @ExampleObject(
                                 name = "Example Update Client",
                                 value =
                                         """
                                         {
                                            "firstName": "Cristiano",
                                            "lastName": "Ronaldo dos Santos",
                                            "cellPhone": 3462257293,
                                            "address": "Cl 1 # 11 - 11",
                                            "email": "cristiano@ronaldo.com"
                                         }
                                         """
                         )
                 )
         )
 )
 @ApiResponse(responseCode = "200", description = "Client updated successfully")
 @ApiResponse(responseCode = "400", description = "Invalid client data")
 @ApiResponse(responseCode = "404", description = "Client not found")
 @ApiResponse(responseCode = "500", description = "Internal server error")
 public ResponseEntity<Client> update(
         @Parameter(description = "ID of the client to be updated", example = "2552243", required = true)
         @PathVariable("id") String clientId,
         @RequestBody Client client) {
  return clientService.update(clientId, client)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
 }

 @DeleteMapping("/{id}")
 @Operation(summary = "Delete a client by ID", description = "Deletes a client if it exists")
 @ApiResponse(responseCode = "200", description = "Client deleted successfully")
 @ApiResponse(responseCode = "400", description = "Invalid client ID")
 @ApiResponse(responseCode = "401", description = "Unauthorized")
 @ApiResponse(responseCode = "403", description = "Forbidden")
 @ApiResponse(responseCode = "404", description = "Client not found")
 @ApiResponse(responseCode = "500", description = "Internal server error")
 public ResponseEntity<Client> delete(
         @Parameter(description = "ID of the client to be deleted", example = "1001",
                 required = true)
         @PathVariable("id") String clientId) {
  if (clientService.delete(clientId)) {
   return ResponseEntity.ok().build();
  } else {
   return ResponseEntity.notFound().build();
  }
 }
}