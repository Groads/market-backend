package com.merida.tecnm.market_backend_v2.web.controller;

import com.merida.tecnm.market_backend_v2.domain.Purchase;
import com.merida.tecnm.market_backend_v2.domain.service.PurchaseService;
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
@RequestMapping("/purchases")
@Tag(name = "Purchase", description = "Manage your purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("")
    @Operation(summary = "Get all purchases",
            description = "Return a list of all registered purchases")
    @ApiResponse(responseCode = "200",
            description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500",
            description = "Internal Server Error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @Operation(summary = "Get purchases by client ID",
            description = "Return all purchases made by a specific client")
    @ApiResponse(responseCode = "200",
            description = "Purchase(s) found for the client")
    @ApiResponse(responseCode = "404",
            description = "Purchase(s) not found for the client")
    @ApiResponse(responseCode = "500",
            description = "Internal Server Error")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "Client ID",
                    example = "4546221",
                    required = true)
            @PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new purchase (Master-Detail)",
            description = "Register a new purchase header and its detail items in a single transaction",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Purchase",
                                    value =
                                            """
                                            {
                                               "clientId": "4546221",
                                               "date": "2026-11-19T14:30:00",
                                               "paymentMethod": "E",
                                               "comment": "Compra en mostrador",
                                               "state": "P",
                                               "items": [
                                                  {
                                                     "productId": 1,
                                                     "quantity": 2,
                                                     "total": 39.00,
                                                     "active": true
                                                  },
                                                  {
                                                     "productId": 3,
                                                     "quantity": 1,
                                                     "total": 25.50,
                                                     "active": true
                                                  }
                                               ]
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}