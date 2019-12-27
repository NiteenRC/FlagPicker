package com.flagpicker.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagpicker.exception.ResourceNotFoundException;
import com.flagpicker.model.Continent;
import com.flagpicker.model.Country;
import com.flagpicker.util.ErrorMessage;

/**
 * This ContinentService implements an methods to provide all data, countries
 * and flag based on user inputs
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 * @see IContinentService
 */
@Service
public class ContinentService implements IContinentService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private List<Continent> continents;

	@Value("${json.file.path}")
	private String jsonFilePath;

	/**
	 * This method used to read JSON file only once at loading and add data into
	 * continents list and this is used in other service to fetch countries and
	 * flags
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	public void init() throws IOException {
		Resource resource = resourceLoader.getResource(jsonFilePath);
		InputStream inputStream = resource.getInputStream();
		TypeReference<List<Continent>> typeReference = new TypeReference<List<Continent>>() {
		};
		continents = objectMapper.readValue(inputStream, typeReference);
	}

	/**
	 * This method used to fetch all continents with countries and respective flags
	 * 
	 * @throws ResourceNotFoundException
	 * @return List<Continent>
	 */
	@Override
	public List<Continent> fetchAll() throws ResourceNotFoundException {
		if (continents.isEmpty()) {
			throw new ResourceNotFoundException(ErrorMessage.CONTINENTS_NOT_FOUND);
		} else {
			return continents;
		}
	}

	/**
	 * This method used to fetch respective countries by continent name
	 * 
	 * @throws ResourceNotFoundException
	 * @param continentName is name of continent
	 * @return List<Country>
	 */
	@Override
	public List<Country> fetchCountriesByContinentName(String continentName) {
		List<Continent> continentts = continents.stream()
				.filter(continent -> continent.getContinent().toLowerCase().equals(continentName.toLowerCase()))
				.collect(Collectors.toList());

		if (continentts.isEmpty())
			throw new ResourceNotFoundException(ErrorMessage.CONTINENT_NOT_FOUND);

		List<Country> countries = continentts.stream().map(continent -> continent.getCountries()).flatMap(List::stream)
				.collect(Collectors.toList());

		if (countries.isEmpty())
			throw new ResourceNotFoundException(ErrorMessage.COUNTRIES_NOT_FOUND);

		return countries;
	}

	/**
	 * This method used to fetch respective flag by country name
	 * 
	 * @throws ResourceNotFoundException
	 * @param countryName is name of country
	 * @return String
	 */
	@Override
	public String fetchFlagByCountryName(String countryName) {
		List<Country> countries = continents.stream().map(continent -> continent.getCountries()).flatMap(List::stream)
				.filter(country -> countryName.toLowerCase().equals(country.getName().toLowerCase()))
				.collect(Collectors.toList());

		if (countries.isEmpty())
			throw new ResourceNotFoundException(ErrorMessage.COUNTRY_NOT_FOUND);

		String flag = countries.stream().map(country -> country.getFlag()).filter(Objects::nonNull)
				.map(Object::toString).collect(Collectors.joining());

		if (flag.isEmpty())
			throw new ResourceNotFoundException(ErrorMessage.COUNTRY_FLAG_NOT_FOUND);

		return flag;
	}
}
