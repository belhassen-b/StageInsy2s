package com.example.tpecommerce.controller;


import com.example.tpecommerce.service.impl.ClientServiceImpl;
import dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientServiceImpl clientService;

    @PostMapping("/save")
    public ResponseEntity<String> createClient(@RequestBody ClientDTO clientDto) {

        clientService.create(clientDto);
        return ResponseEntity.ok("Client created");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllClients() {
        if (clientService.findAll() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientService.findAll().toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id") Integer id) {
        if (clientService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteById(id);
        return ResponseEntity.ok("Client deleted");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getClientById(@PathVariable("id") Integer id) {
        if (clientService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientService.findById(id).toString());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateClientById(@PathVariable("id") Integer id, @RequestBody ClientDTO clientDto) {
        if (clientService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.update(id, clientDto);
        return ResponseEntity.ok("Client updated");
    }
}
