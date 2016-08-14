package com.example.contacts.rest.services.impl;

import com.example.contacts.rest.mediatypes.Contact;
import com.example.contacts.rest.services.ResourceService;
import com.example.contacts.service.ContactService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ContactService contactService;

    @Autowired
    private EntityLinks entityLinks;

    @Override
    public Resource<Contact> getContactResource(Integer contactId, Contact contactMediaType, boolean generateBackLink) {
        Resource<Contact> contactResource = new Resource<>(contactMediaType);
        LinkBuilder lb = entityLinks.linkFor(Contact.class, contactId, contactMediaType.getId());
        contactResource.add(lb.slash(contactMediaType).withSelfRel());
        if (generateBackLink) {
            contactResource.add(lb.withRel("contacts"));
        }
        return contactResource;
    }

    @Override
    public Contact getContactMediaType(com.example.contacts.domain.Contact contact) {
        Contact contactMediaType = new Contact();
        BeanUtils.copyProperties(contact, contactMediaType);

        if (contact.getName() != null) {
            contactMediaType.setName(contact.getName());
        }
        if (contact.getEmail() != null) {
            contactMediaType.setEmail(contact.getEmail());
        }
        if (contact.getProfession() != null) {
            contactMediaType.setProfession(contact.getProfession());
        }

        return contactMediaType;
    }

    @Override
    public com.example.contacts.domain.Contact getContactDomain(Contact contact) throws Throwable {
        com.example.contacts.domain.Contact newContact = new com.example.contacts.domain.Contact();
        BeanUtils.copyProperties(contact, newContact);

        if (contact.getName() != null) {
            newContact.setName(contact.getName());
        }
        if (contact.getEmail() != null) {
            newContact.setEmail(contact.getEmail());
        }
        if (contact.getProfession() != null) {
            newContact.setProfession(contact.getProfession());
        }

        return newContact;
    }
}
