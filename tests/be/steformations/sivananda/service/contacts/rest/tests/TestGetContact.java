package be.steformations.sivananda.service.contacts.rest.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.steformations.sivananda.data.contacts.dto.ContactDto;
import be.steformations.sivananda.data.contacts.dto.CountryDto;
import be.steformations.sivananda.data.contacts.dto.TagDto;
import be.steformations.sivananda.service.contacts.rest.ContactRestClient;

public class TestGetContact {

	private ContactRestClient client;

	@Before
	public void setUp() throws Exception {
		this.client = new ContactRestClient();
	}

	@Test
	public void testGetContactById() {
		ContactDto betty = this.createBettyBoop();
		ContactDto found = this.client.getContactById(betty.getId());
		assertEquals(betty, found);
	}

	@Test
	public void testGetAllContacts() {
		ContactDto betty = this.createBettyBoop();
		List<ContactDto> contacts = this.client.getAllContacts();
		assertTrue(contacts.contains(betty));
	}
	
	@Test
	public void testCreateAndSaveContactAndRemove() {
		ContactDto _new = this.createContact();
		List<String> tags = new ArrayList<>();
		for (TagDto t : _new.getTags()){
			tags.add(t.getValue());
		}
		String abbreviation = _new.getCountry().getAbbreviation();
		ContactDto received = this.client.createAndSaveContact(_new.getFirstname(), _new.getName(), _new.getEmail(), abbreviation, tags);
		
		assertNotNull(received);
		assertEquals(_new.getFirstname(), received.getFirstname());
		assertEquals(_new.getName(), received.getName());
		assertEquals(_new.getEmail(), received.getEmail());
		assertEquals(_new.getCountry(), received.getCountry());
		assertEquals(_new.getTags(), received.getTags());
		
		boolean deleted = this.client.removeContact(received.getId());
		assertTrue(deleted);
	}
	
	@Test
	public void testRemoveContact() {

	}
	
	private ContactDto createBettyBoop(){
		CountryDto usa = new CountryDto();
		usa.setId(1);
		usa.setAbbreviation("US");
		usa.setName("Etats-Unis");
		
		TagDto sexsymbol = new TagDto();
		sexsymbol.setId(1);
		sexsymbol.setValue("sex-symbol");
		
		TagDto vamp = new TagDto();
		vamp.setId(2);
		vamp.setValue("vamp");
		
		ContactDto contact = new ContactDto();
		contact.setId(1);
		contact.setFirstname("Betty");
		contact.setName("Boop");
		contact.setEmail("betty.boop@hollywood.com");
		contact.setCountry(usa);
		contact.getTags().add(sexsymbol);
		contact.getTags().add(vamp);
		
		return contact;
	}
	
	private ContactDto createContact(){
		CountryDto usa = new CountryDto();
		usa.setId(1);
		usa.setAbbreviation("US");
		usa.setName("Etats-Unis");
		
		TagDto sexsymbol = new TagDto();
		sexsymbol.setId(1);
		sexsymbol.setValue("sex-symbol");
		
		TagDto vamp = new TagDto();
		vamp.setId(2);
		vamp.setValue("vamp");
		
		ContactDto contact = new ContactDto();
		contact.setId(0);
		contact.setFirstname("Betty-" + System.currentTimeMillis());
		contact.setName("Boop-" + System.currentTimeMillis());
		contact.setEmail(contact.getFirstname() + "@hollywood.com");
		contact.setCountry(usa);
		contact.getTags().add(sexsymbol);
		contact.getTags().add(vamp);
		
		return contact;
	}

}
