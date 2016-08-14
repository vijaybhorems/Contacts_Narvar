package com.example.contacts.rest.services;

import com.example.contacts.rest.mediatypes.Contact;
import org.springframework.hateoas.Resource;

public interface ResourceService {

    Resource<Contact> getContactResource(Integer contactId, Contact contactMediaType, boolean generateBackLink);

    Contact getContactMediaType(com.example.contacts.domain.Contact contact);

    com.example.contacts.domain.Contact getContactDomain(Contact contact) throws Throwable;
}
