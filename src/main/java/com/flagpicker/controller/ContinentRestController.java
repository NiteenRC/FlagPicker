package com.flagpicker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flagpicker.model.Continent;
import com.flagpicker.model.Country;
import com.flagpicker.service.IContinentService;

@RestController
@RequestMapping("/continent")
public class ContinentRestController {

	@Autowired
	private IContinentService continentService;


	@GetMapping("/fetchAll")
	public ResponseEntity<List<Continent>> fetchAllWorld() {
		return ResponseEntity.ok().body(continentService.fetchAll());
	}

	@GetMapping("/{continentName}")
	public ResponseEntity<List<Country>> fetchCountriesByContinentName(@PathVariable String continentName) {
		return ResponseEntity.ok().body(continentService.fetchCountriesByContinentName(continentName));
	}

	@GetMapping("/country/{countryName}")
	public ResponseEntity<String> fetchFlagByCountryName(@PathVariable String countryName) {
		return ResponseEntity.ok().body(continentService.fetchFlagByCountryName(countryName));
	}
}