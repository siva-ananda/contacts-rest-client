package be.steformations.sivananda.service.contacts.rest;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import be.steformations.sivananda.data.contacts.dto.ContactDto;
import be.steformations.sivananda.data.contacts.dto.ContactsDtoFactory;
import be.steformations.sivananda.data.contacts.dto.CountryDto;
import be.steformations.sivananda.data.contacts.dto.TagDto;

public class ContactRestClient {

	private WebTarget service;
	private ContactsDtoFactory contactsDtoFactory;

	public ContactRestClient() {
		super();
		this.service = ClientBuilder.newClient().target("http://localhost:8080/contacts-rest/rs");
		this.contactsDtoFactory = new ContactsDtoFactory();
	}

	public String getTagValueByIdAsText(int id) {
		String value = null;

		value = this.service.path("tag").path(String.valueOf(id)).path("value").request(MediaType.TEXT_PLAIN)
				.get(String.class);

		return value;
	}

	public TagDto getTagById(int id) {
		TagDto dto = null;

		String xml = this.service.path("tag").path(String.valueOf(id)).request(MediaType.TEXT_PLAIN).get(String.class);
		TagDto temp = this.contactsDtoFactory.xmlToTag(xml);

		if (temp.getId() != null) {
			dto = temp;
		}
		return dto;
	}

	public TagDto createAndSaveTag(String value) {
		TagDto dto = null;
		dto = this.service.path("tag").path(value).request(MediaType.APPLICATION_XML).post(null, TagDto.class);

		return dto;
	}

	public TagDto getTagByValue(String value) {
		TagDto dto = null;

		Response response = this.service.path("tag").path(value).request(MediaType.APPLICATION_XML).get();

		if (response.getStatus() == 200) {
			dto = response.readEntity(TagDto.class);
		}

		return dto;
	}

	public boolean removeTag(int id) {
		boolean deleted = false;

		Response response = this.service.path("tag").path(String.valueOf(id)).request().delete();
		if (response.getStatus() == 200) {
			deleted = true;
		}
		return deleted;
	}

	public List<TagDto> getAllTags() {
		List<TagDto> tags = null;

		Response response = this.service.path("tag").request(MediaType.APPLICATION_XML).get();
		if (response.getStatus() == 200) {
			GenericType<List<TagDto>> type = new GenericType<List<TagDto>>() {
			};
			tags = response.readEntity(type);
		} else {
			tags = Collections.emptyList();
		}
		return tags;
	}

	public CountryDto getCountryByAbbreviation(String abbreviation) {
		CountryDto country = null;
		Response response = this.service.path("country").path(abbreviation).request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == 200) {
			country = response.readEntity(CountryDto.class);
		}
		return country;
	}

	public List<CountryDto> getAllCountries() {
		List<CountryDto> countries = null;
		Response response = this.service.path("country").request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == 200) {
			GenericType<List<CountryDto>> type = new GenericType<List<CountryDto>>() {
			};
			countries = response.readEntity(type);
		}
		return countries;
	}

	public CountryDto createAndSaveCountry(String abbreviation, String name) {
		CountryDto country = null;

		MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
		form.add("abbr", abbreviation);
		form.add("name", name);

		Entity<MultivaluedMap<String, String>> entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);
		Response response = this.service.path("country").request(MediaType.APPLICATION_JSON).post(entity);

		if (response.getStatus() == 200) {
			country = response.readEntity(CountryDto.class);
		}

		return country;
	}

	public ContactDto getContactById(int id) {
		ContactDto contact = null;
		Response response = this.service.path("contact").path(String.valueOf(id)).request(MediaType.APPLICATION_XML)
				.get();
		if (response.getStatus() == 200) {
			contact = response.readEntity(ContactDto.class);
		}
		return contact;
	}

	public List<ContactDto> getAllContacts() {
		List<ContactDto> contacts = null;
		Response response = this.service.path("contact").request(MediaType.APPLICATION_XML).get();
		if (response.getStatus() == 200) {
			GenericType<List<ContactDto>> type = new GenericType<List<ContactDto>>() {
			};
			contacts = response.readEntity(type);
		}
		return contacts;
	}

	public boolean removeContact(int id) {
		boolean deleted = false;

		Response response = this.service.path("contact").path(String.valueOf(id)).request().delete();
		if (response.getStatus() == 200) {
			deleted = true;
		}
		return deleted;
	}

	public ContactDto createAndSaveContact(String firstname, String name, String email, String countryAbbreviation, List<String> tagValues) {
		ContactDto contactDto = null;
		ContactDto input = new ContactDto();
		input.setFirstname(firstname);
		input.setName(name);
		input.setEmail(email);
		
		CountryDto countryDto = null;
		if (countryAbbreviation != null){
			countryDto = new CountryDto();
			countryDto.setAbbreviation(countryAbbreviation);
		}
		input.setCountry(countryDto);
		
		if (tagValues != null) {
			for (String value : tagValues){
				TagDto tagDto = new TagDto();
				tagDto.setValue(value);
				input.getTags().add(tagDto);
			}
		}
		
		Entity<ContactDto> entity = Entity.entity(input, MediaType.APPLICATION_XML);
		
		Response response = this.service.path("contact").request(MediaType.APPLICATION_XML).post(entity);
		
		if (response.getStatus() == 200){
			contactDto = response.readEntity(ContactDto.class);
		}

		return contactDto;
	}

}
