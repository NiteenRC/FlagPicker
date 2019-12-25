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
import com.flagpicker.exception.ContinentNotFoundException;
import com.flagpicker.exception.CountryNotFoundException;
import com.flagpicker.model.Continent;
import com.flagpicker.model.Country;
import com.flagpicker.util.ErrorMessage;

@Service
public class ContinentServiceImpl implements ContinentService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private List<Continent> continents;

	@Value("${json.file.path}")
	private String jsonFilePath;

	@PostConstruct
	public void init() throws IOException {
		Resource resource = resourceLoader.getResource(jsonFilePath);
		InputStream inputStream = resource.getInputStream();
		TypeReference<List<Continent>> typeReference = new TypeReference<List<Continent>>() {
		};
		continents = objectMapper.readValue(inputStream, typeReference);
	}

	@Override
	public List<Continent> fetchAll() {
		if (continents.isEmpty()) {
			throw new ContinentNotFoundException(ErrorMessage.CONTINENTS_NOT_FOUND);
		} else {
			return continents;
		}
	}

	@Override
	public List<Country> fetchCountriesByContinentName(String continentName) {
		List<Continent> continentts = continents.stream()
				.filter(continent -> continent.getContinent().equals(continentName)).collect(Collectors.toList());

		if (continentts.isEmpty())
			throw new ContinentNotFoundException(ErrorMessage.CONTINENT_NOT_FOUND);

		List<Country> countries = continentts.stream().map(continent -> continent.getCountries()).flatMap(List::stream)
				.collect(Collectors.toList());

		if (countries.isEmpty())
			throw new ContinentNotFoundException(ErrorMessage.COUNTRIES_NOT_FOUND);

		return countries;
	}

	@Override
	public String fetchFlagByCountryName(String countryName) {
		List<Country> countries = continents.stream().map(continent -> continent.getCountries()).flatMap(List::stream)
				.filter(country -> countryName.equals(country.getName())).collect(Collectors.toList());

		if (countries.isEmpty())
			throw new CountryNotFoundException(ErrorMessage.COUNTRY_NOT_FOUND);

		String flag = countries.stream().map(country -> country.getFlag()).filter(Objects::nonNull)
				.map(Object::toString).collect(Collectors.joining());

		if (flag.isEmpty())
			throw new CountryNotFoundException(ErrorMessage.COUNTRY_FLAG_NOT_FOUND);

		return flag;
	}
}
