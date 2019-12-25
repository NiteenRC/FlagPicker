package com.flagpicker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flagpicker.service.ContinentService;

@RestController
@RequestMapping("/flagpicker")
public class ContinentController {

	@Autowired
	private ContinentService continentService;

	@GetMapping("/fetchAll")
	public ResponseEntity<Object> fetchAllWorld() {
		return new ResponseEntity<Object>(continentService.fetchAll(), HttpStatus.OK);
	}

	@GetMapping("/continent/{continentName}")
	public ResponseEntity<Object> fetchCountriesByContinentName(@PathVariable String continentName) {
		return new ResponseEntity<Object>(continentService.fetchCountriesByContinentName(continentName), HttpStatus.OK);
	}

	@GetMapping("/country/{countryName}")
	public ResponseEntity<Object> fetchFlagByCountryName(@PathVariable String countryName) {
		return new ResponseEntity<Object>(continentService.fetchFlagByCountryName(countryName), HttpStatus.OK);
	}
}
