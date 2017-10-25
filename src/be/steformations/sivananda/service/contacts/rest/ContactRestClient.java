package be.steformations.sivananda.service.contacts.rest;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import be.steformations.sivananda.data.contacts.dto.ContactsDtoFactory;
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
		dto = this.contactsDtoFactory.xmlToTag(xml);

		return dto;
	}

	public TagDto createAndSaveTag(String value) {
		TagDto dto = null;
		dto = this.service.path("tag").path(value).request(MediaType.APPLICATION_XML).post(null, TagDto.class);

		return dto;
	}

}
