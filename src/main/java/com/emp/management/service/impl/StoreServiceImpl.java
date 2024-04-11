package com.emp.management.service.impl;

import com.emp.management.model.Store;
import com.emp.management.repository.StoreRepository;
import com.emp.management.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Optional<Store> get(String id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isEmpty()) {
            return Optional.empty();
        }

        return storeOptional;
    }

    @Override
    public Store create(Store store) {
        store.setDepartments(new ArrayList<>());
        return storeRepository.save(store);
    }

    @Override
    public Optional<Store> update(Store store, String id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isEmpty()) {
            return Optional.empty();
        }

        Store existingStore = storeOptional.get();
        BeanUtils.copyProperties(store, existingStore);

        return Optional.of(storeRepository.save(existingStore));
    }

    @Override
    public String delete(String id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isEmpty()) {
            return "Store not found with id: " + id;
        }

        storeRepository.deleteById(id);

        return "Store Deleted successfully with id: " + id;
    }
}
