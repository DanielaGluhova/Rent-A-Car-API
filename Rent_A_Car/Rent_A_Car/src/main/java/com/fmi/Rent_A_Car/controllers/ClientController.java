package com.fmi.Rent_A_Car.controllers;


import com.fmi.Rent_A_Car.entities.Car;
import com.fmi.Rent_A_Car.entities.Client;
import com.fmi.Rent_A_Car.http.AppResponse;
import com.fmi.Rent_A_Car.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Създаване на нов клиент
    @PostMapping
    public ResponseEntity<?> createNewClient(@RequestBody Client client) {
        if (this.clientService.createClient(client)) {
            return AppResponse.success()
                    .withMessage("Client created successfully")
                    .build();
        }
        return AppResponse.error().withMessage("Client could not be created").build();
    }

    // Получаване на всички клиенти
    @GetMapping
    public ResponseEntity<?> fetchAllClients() {
        ArrayList<Client> clients = (ArrayList<Client>) this.clientService.getAllClients();
        return AppResponse.success().withData(clients).build();
    }

    // Получаване на клиент по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchClientById(@PathVariable int id) {
        Client client = this.clientService.getClient(id);
        if (client == null) {
            return AppResponse.error().withMessage("Client not found").build();
        }
        return AppResponse.success().withData(client).build();
    }

    // Актуализиране на клиент
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@RequestBody Client client, @PathVariable("id") int id) {
        Client clientToUpdate = clientService.getClient(id);
        clientToUpdate.setHas_incidents(client.getHas_incidents());
        clientToUpdate.setAddress(client.getAddress());
        clientToUpdate.setAge(client.getAge());
        clientToUpdate.setName(client.getName());
        clientToUpdate.setPhone(client.getPhone());

        boolean isUpdated = this.clientService.updateClient(client);
        if (isUpdated) {
            return AppResponse.success().withMessage("Client updated successfully").build();
        }
        return AppResponse.error().withMessage("Client could not be updated").build();
    }

    // Изтриване на клиент
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        boolean isDeleted = this.clientService.deleteClient(id);
        if (isDeleted) {
            return AppResponse.success().withMessage("Client deleted successfully").build();
        }
        return AppResponse.error().withMessage("Client could not be deleted").build();
    }
}
