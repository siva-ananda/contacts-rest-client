package be.steformations.sivananda.service.contacts.rest.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.sivananda.data.contacts.dto.CountryDto;
import be.steformations.sivananda.data.contacts.dto.TagDto;
import be.steformations.sivananda.service.contacts.rest.ContactRestClient;

public class TestGetCountryByAbbreviation {

	private ContactRestClient client;

	@Before
	public void setUp() throws Exception {
		this.client = new ContactRestClient();
	}

	@Test
	public void testGetCountryByAbbreviation() {
		CountryDto usa = new CountryDto();
		usa.setId(1);
		usa.setName("Etats-Unis");
		usa.setAbbreviation("US");

		CountryDto found = this.client.getCountryByAbbreviation("US");
		assertEquals(usa, found);
	}

}
