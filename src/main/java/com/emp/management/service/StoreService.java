package com.emp.management.service;

import com.emp.management.model.Store;

import java.util.Optional;

public interface StoreService {

    public Optional<Store> get(String id);

    public Store create(Store store);

    public Optional<Store> update(Store store, String id);

    public String delete(String id);
}
