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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/search", produces = {MediaTypes.HAL_JSON_VALUE})
@ExposesResourceFor(Contact.class)
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET)
    HttpEntity<?> queryContacts(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "profession", required = false) String profession) throws Throwable {
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(email) && StringUtils.isEmpty(profession)) {
            return getContacts();
        } else if (StringUtils.isEmpty(email) && StringUtils.isEmpty(profession)) {
            return getContactsByName(name);
        } else if (StringUtils.isEmpty(name) && StringUtils.isEmpty(profession)) {
            return getContactByEmail(email);
        } else if (StringUtils.isEmpty(name) && StringUtils.isEmpty(email)) {
            return getContactsByProfession(profession);
        } else if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(email) && StringUtils.isEmpty(profession)) {
            return getContactsByNameAndEmail(name, email);
        } else if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(profession) && StringUtils.isEmpty(email)) {
            return getContactsByNameAndProfession(name, profession);
        } else if (!StringUtils.isEmpty(profession) && !StringUtils.isEmpty(email) && StringUtils.isEmpty(name)) {
            return getContactsByEmailAndProfession(email, profession);
        } else if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(email) && !StringUtils.isEmpty(profession)) {
            return getContactsByNameAndEmailAndProfession(name, email, profession);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private HttpEntity<?> getContacts() {
        List<com.example.contacts.domain.Contact> contactList = contactService.getAllContacts();
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    private HttpEntity<?> getContactsByName(String name) {
        List<com.example.contacts.domain.Contact> contactList = contactService.getContactsByName(name);
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    private HttpEntity<?> getContactByEmail(String email) {
        List<com.example.contacts.domain.Contact> contactList = contactService.getContactsByEmail(email);
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    private HttpEntity<?> getContactsByProfession(String profession) {
        List<com.example.contacts.domain.Contact> contactList = contactService.getContactsByProfession(profession);
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    private HttpEntity<?> getContactsByNameAndEmail(String name, String email) {
        List<com.example.contacts.domain.Contact> contactList = contactService.getContactsByNameAndEmail(name, email);
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    private HttpEntity<?> getContactsByNameAndProfession(String name, String profession) {
        List<com.example.contacts.domain.Contact> contactList = contactService.getContactsByNameAndProfession(name, profession);
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    private HttpEntity<?> getContactsByEmailAndProfession(String email, String profession) {
        List<com.example.contacts.domain.Contact> contactList = contactService.getContactsByEmailAndProfession(email, profession);
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    private HttpEntity<?> getContactsByNameAndEmailAndProfession(String name, String email, String profession) {
        List<com.example.contacts.domain.Contact> contactList = contactService.getContactsByNameAndEmailAndProfession(name, email, profession);
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.example.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }
}
