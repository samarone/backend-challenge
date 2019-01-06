package com.invillia.acme.service;

import java.util.List;
import java.util.Optional;

import com.invillia.acme.domain.Store;

public interface StoreService {
	
    Store save(Store store);

    Optional<Store> update(Long storeId, Store store);

    List<Store> findStoresByParameters(String name, String address);

}
