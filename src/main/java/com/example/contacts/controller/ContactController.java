package com.example.contacts.controller;

import com.example.contacts.rest.mediatypes.Contact;
import com.example.contacts.rest.services.ResourceService;
import com.example.contacts.service.ContactService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/contacts", produces = {MediaTypes.HAL_JSON_VALUE})
@ExposesResourceFor(Contact.class)
public class ContactController {
    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET)
    HttpEntity<Resources<Resource<Contact>>> getContacts() {
        List<com.example.contacts.domain.Contact> contactList = contactService.getAllContacts();
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Resource<Contact>> createContact(@RequestBody Contact contact) throws Throwable {

        if (StringUtils.isEmpty(contact.getName())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No name provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No name provided");
        }
        if (StringUtils.isEmpty(contact.getEmail())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No email provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No email provided");
        }

        com.example.contacts.domain.Contact newContact = resourceService.getContactDomain(contact);
        newContact.setName(contact.getName());
        newContact.setEmail(contact.getEmail());
        newContact.setProfession(contact.getProfession());

        newContact = contactService.createContact(newContact);

        Contact contactMediaType = resourceService.getContactMediaType(newContact);
        Resource<Contact> contactResource = resourceService.getContactResource(newContact.getId(), contactMediaType, false);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(entityLinks.linkToSingleResource(contactMediaType).getHref()));

        return new ResponseEntity<>(contactResource, headers, HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/{contactId}")
    public HttpEntity<?> updateContact(@PathVariable String contactId, @RequestBody Contact contact) throws Throwable {

        if (StringUtils.isEmpty(contact.getName())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No name provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No name provided");
        }
        if (StringUtils.isEmpty(contact.getEmail())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No email provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No email provided");
        }

        com.example.contacts.domain.Contact existingContact = contactService.getContactById(Integer.valueOf(contactId));

        if (existingContact != null) {
            existingContact.setName(contact.getName());
            existingContact.setEmail(contact.getEmail());
            existingContact.setProfession(contact.getProfession());
            contactService.updateContact(existingContact);

            Contact contactMediaType = resourceService.getContactMediaType(existingContact);
            Resource<Contact> contactResource = resourceService.getContactResource(existingContact.getId(), contactMediaType, false);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(entityLinks.linkToSingleResource(contactMediaType).getHref()));

            return new ResponseEntity<>(contactResource, headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{contactId}")
    public HttpEntity<?> getContactDetails(@PathVariable String contactId) throws Throwable {

        com.example.contacts.domain.Contact contact = contactService.getContactById(Integer.valueOf(contactId));

        if (contact != null) {
            Contact contactMediaType = resourceService.getContactMediaType(contact);
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), contactMediaType, false);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(entityLinks.linkToSingleResource(contactMediaType).getHref()));

            return new ResponseEntity<>(contactResource, headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
