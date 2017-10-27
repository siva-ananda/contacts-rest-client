package be.steformations.sivananda.service.contacts.rest.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.sivananda.data.contacts.dto.CountryDto;
import be.steformations.sivananda.service.contacts.rest.ContactRestClient;

public class TestCreateAndSaveCountry {

	private ContactRestClient client;

	@Before
	public void setUp() throws Exception {
		this.client = new ContactRestClient();
	}

	@Test
	public void test() {
		long millis = System.currentTimeMillis();
		String abbreviation = "BE-" + millis;
		String name = "Belgique" + millis;

		CountryDto _new = new CountryDto();
		_new.setAbbreviation(abbreviation);
		_new.setName(name);

		CountryDto received = this.client.createAndSaveCountry(abbreviation, name);
		assertNotNull(received);
		assertEquals(_new.getAbbreviation(), received.getAbbreviation());
		assertEquals(_new.getName(), received.getName());

		CountryDto found = this.client.getCountryByAbbreviation(abbreviation);
		assertEquals(received, found);

	}

}
