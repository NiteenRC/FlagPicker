package com.flagpicker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flagpicker.model.Continent;
import com.flagpicker.model.Country;

/**
 * This IContinentService has an abstract methods to fetch all data, countries
 * and flag based on user inputs
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 */
@Service
public interface IContinentService {
	/**
	 * Returns all data
	 * 
	 * @return List<Continent>
	 */
	List<Continent> fetchAll();

	/**
	 * Returns countries based on continent name provided
	 * 
	 * @param continentName
	 * @return List<Country>
	 */
	List<Country> fetchCountriesByContinentName(String continentName);

	/**
	 * Returns flag based on country name provided
	 * 
	 * @param countryName
	 * @return
	 */
	String fetchFlagByCountryName(String countryName);
}
