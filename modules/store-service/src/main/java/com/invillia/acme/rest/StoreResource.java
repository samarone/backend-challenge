package com.invillia.acme.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StoreResource {
	
	@GetMapping("/list")
	public ResponseEntity<List<Object>> example() {
		return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
	}

}
