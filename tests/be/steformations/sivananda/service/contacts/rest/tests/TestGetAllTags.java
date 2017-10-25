package be.steformations.sivananda.service.contacts.rest.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.steformations.sivananda.data.contacts.dto.TagDto;
import be.steformations.sivananda.service.contacts.rest.ContactRestClient;

public class TestGetAllTags {

	private ContactRestClient client;

	@Before
	public void setUp() throws Exception {
		this.client = new ContactRestClient();
	}

	@Test
	public void testGetAllTags() {
		TagDto sexsymbol = new TagDto();
		sexsymbol.setId(1);
		sexsymbol.setValue("sex-symbol");
		
		TagDto vamp = new TagDto();
		vamp.setId(2);
		vamp.setValue("vamp");
		
		List<TagDto> tags = this.client.getAllTags();
		
		assertTrue(tags.contains(vamp));
		assertTrue(tags.contains(sexsymbol));
	}

}
