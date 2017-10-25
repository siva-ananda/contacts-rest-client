package be.steformations.sivananda.service.contacts.rest;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ContactRestClient {

	private WebTarget service;

	public ContactRestClient() {
		super();
		this.service = ClientBuilder.newClient().target("http://localhost:8080/contacts-rest/rs");
	}

	public String getTagValueByIdAsText(int id) {
		String value = null;

		value = this.service.path("tag").path(String.valueOf(id)).path("value").request(MediaType.TEXT_PLAIN)
				.get(String.class);

		return value;
	}

}
