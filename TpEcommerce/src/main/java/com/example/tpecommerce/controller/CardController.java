package com.example.tpecommerce.controller;


import com.example.tpecommerce.service.impl.CardServiceImpl;
import dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardController {

    private final CardServiceImpl cardService;

    @PostMapping("/save")
    public ResponseEntity<String> createCard(@RequestBody CardDTO cardDto) {
        cardService.create(cardDto);
        return ResponseEntity.ok("Card created");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllCards() {
        if (cardService.findAll() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cardService.findAll().toString());
    }

    @DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteCardById(@PathVariable("id") Integer id) {
        if (cardService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        cardService.deleteById(id);
        return ResponseEntity.ok("Card deleted");
    }

@GetMapping("/get/{id}")
public ResponseEntity<String> getCardById(@PathVariable("id") Integer id) {
        if (cardService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cardService.findById(id).toString());
    }

    @PutMapping("/update/{id}")
public ResponseEntity<String> updateCardById(@PathVariable("id") Integer id, @RequestBody CardDTO cardDto) {
        if (cardService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        cardService.update(id, cardDto);
        return ResponseEntity.ok("Card updated");
    }

}
