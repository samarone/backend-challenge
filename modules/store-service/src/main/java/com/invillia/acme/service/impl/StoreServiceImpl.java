package com.invillia.acme.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Store;
import com.invillia.acme.exceptions.BusinessException;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.repository.specs.StoreSpecification;
import com.invillia.acme.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepository;

	@Override
	public Store save(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public Optional<Store> update(Long storeId, @Valid Store store) {

		Optional<Store> target = storeRepository.findById(store.getId());

		if (target.isPresent()) {
			target.get().setName(store.getName());
			target.get().setAddress(store.getAddress());
			return Optional.of(storeRepository.save(target.get()));
		}

		throw new BusinessException("Store with id: "+ storeId +" not found");
	}

	@Override
	public List<Store> findStoresByParameters(String name, String address) {
		Specification<Store> spec = Specification.where(StoreSpecification.nameEqual(name))
				.and(StoreSpecification.addressEqual(address));
		return storeRepository.findAll(spec);
	}

}
