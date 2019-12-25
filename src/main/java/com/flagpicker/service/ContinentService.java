package com.flagpicker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flagpicker.model.Continent;
import com.flagpicker.model.Country;

@Service
public interface ContinentService {
	List<Continent> fetchAll();

	List<Country> fetchCountriesByContinentName(String continentName);
	
	String fetchFlagByCountryName(String countryName);
}
