package be.steformations.sivananda.service.contacts.rest.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.sivananda.data.contacts.dto.TagDto;
import be.steformations.sivananda.service.contacts.rest.ContactRestClient;

public class TestGetTagById {

	private ContactRestClient client;

	@Before
	public void setUp() throws Exception {
		this.client = new ContactRestClient();
	}

	@Test
	public void testGetTagById() {
		TagDto tag = this.client.getTagById(1);

		assertNotNull(tag);
		assertEquals(new Integer(1), tag.getId());
		assertEquals("sex-symbol", tag.getValue());
	}

}
