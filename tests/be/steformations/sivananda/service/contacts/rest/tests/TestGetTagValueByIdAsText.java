package be.steformations.sivananda.service.contacts.rest.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.sivananda.service.contacts.rest.ContactRestClient;

public class TestGetTagValueByIdAsText {
	
	private ContactRestClient client;

	@Before
	public void setUp() throws Exception {
		this.client = new ContactRestClient();
	}

	@Test
	public void test() {
		String sexsymbol = this.client.getTagValueByIdAsText(1);
		assertEquals("sex-symbol", sexsymbol);
		
		String vamp = this.client.getTagValueByIdAsText(2);
		assertEquals("vamp", vamp);
	}

}
