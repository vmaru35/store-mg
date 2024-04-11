package com.emp.management.controller;

import com.emp.management.model.Store;
import com.emp.management.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/{id}")
    public ResponseEntity getStore(@PathVariable("id") String id){
        return ResponseEntity.of(storeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        return ResponseEntity.accepted().body(storeService.create(store));
    }

    @PutMapping
    public ResponseEntity updateStore(@RequestBody Store store, @PathVariable("id") String id) {
        Optional<Store> storeOptional = storeService.update(store, id);

        if (storeOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Store not found with id: " + id);
        }

        return ResponseEntity.accepted().body(storeOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStore(@PathVariable("id") String id){
        return ResponseEntity.accepted().body(storeService.delete(id));
    }


}
