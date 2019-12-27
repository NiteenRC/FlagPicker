package com.flagpicker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.flagpicker.exception.ResourceNotFoundException;
import com.flagpicker.model.Continent;
import com.flagpicker.model.Country;

/**
 * This ContinentServiceImplTest has test cases to cover all test scenarios.
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ContinentServiceImplTest {

	@Value("${json.file.path}")
	private String jsonFilePath;

	@Autowired
	private IContinentService continentService;

	@Test
	public void testValidaJsonFileName() {
		assertTrue(jsonFilePath.endsWith(".json"));
	}

	@Test
	public void testFetchAll() throws IOException {
		List<Continent> continents = continentService.fetchAll();
		List<Country> countries = continents.stream().map(continent -> continent.getCountries()).flatMap(List::stream)
				.collect(Collectors.toList());
		assertThat(continents).isNotNull().isNotEmpty();
		assertThat(countries).isNotNull().isNotEmpty();
	}

	@Test
	public void testFetchCountriesByContinentName() {
		List<Country> countries = continentService.fetchCountriesByContinentName("Africa");
		assertThat(countries).isNotNull().isNotEmpty();
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testFetchCountriesByContinentNameWithNoContinent() {
		continentService.fetchCountriesByContinentName("Nigeria");// Nigeria not a continent
	}

	@Test
	public void testFetchFlagByCountryName() {
		String flag = continentService.fetchFlagByCountryName("Nigeria");
		assertThat(flag).isNotNull().isNotEmpty();
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testFetchFlagByCountryNameWithNoCountry() {
		continentService.fetchFlagByCountryName("Africa");// Africa is not country
	}
}
