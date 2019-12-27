package com.flagpicker.model;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This Continent is POJO class, it has continent name and list of countries
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 */
@Component
public class Continent {
	private String continent;
	private List<Country> countries;

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}
