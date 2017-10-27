package be.steformations.sivananda.service.contacts.rest.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.steformations.sivananda.data.contacts.dto.CountryDto;
import be.steformations.sivananda.service.contacts.rest.ContactRestClient;

public class TestGetAllCountries {

	private ContactRestClient client;

	@Before
	public void setUp() throws Exception {
		this.client = new ContactRestClient();
	}

	@Test
	public void testGetAllCountries() {
		CountryDto usa = new CountryDto();
		usa.setId(1);
		usa.setName("Etats-Unis");
		usa.setAbbreviation("US");

		List<CountryDto> countries = this.client.getAllCountries();

		assertTrue(countries.contains(usa));
	}

}
