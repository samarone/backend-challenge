package com.invillia.acme.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.service.StoreService;

import io.swagger.annotations.ApiOperation;

@RestController("/api")
public class StoreResource {

	@Autowired
	StoreService storeService;

	ModelMapper mapper;

	public StoreResource() {
		super();
		mapper = new ModelMapper();
	}

	@ApiOperation(value = "Create a new store")
	@PostMapping("/")
	public ResponseEntity<StoreDTO> create(@RequestBody @Valid StoreDTO storeDTO) {
		Store newStore = mapper.map(storeDTO, Store.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(storeService.save(newStore), StoreDTO.class));
	}

	@ApiOperation(value = "Update a exists store")
	@PutMapping("/{storeId}")
	public ResponseEntity<StoreDTO> update(@PathVariable(required = true) Long storeId,
			@RequestBody @Valid StoreDTO dto) {
		Store store = mapper.map(dto, Store.class);
		store.setId(storeId);
		StoreDTO updated = mapper.map(storeService.update(storeId, store).get(), StoreDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@ApiOperation(value = "Return stores with filter by parameters")
	@GetMapping("/list")
	public ResponseEntity<List<StoreDTO>> findStores(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "address", required = false) String address) {
		
		List<Store> stores = storeService.findStoresByParameters(name, address);


		if (stores.isEmpty()) {
			return new ResponseEntity<List<StoreDTO>>(HttpStatus.NO_CONTENT);
		}
		
		List<StoreDTO> storesDTO = stores.stream().map(this::toDTO).collect(Collectors.toList());

		return new ResponseEntity<List<StoreDTO>>(storesDTO, HttpStatus.OK);
	}

	private StoreDTO toDTO(Store store) {
		return mapper.map(store, StoreDTO.class);
	}

}
