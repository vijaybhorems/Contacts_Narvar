package com.example.contacts.service.impl;

import com.example.contacts.domain.Contact;
import com.example.contacts.repository.ContactRepository;
import com.example.contacts.service.ContactService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact createContact(Contact contact) {
        Contact newContact = contactRepository.save(contact);
        return newContact;
    }

    @Override
    public List<Contact> getAllContacts() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Override
    public Contact getContactById(Integer id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact existingContact = getContactById(contact.getId());
        return (existingContact == null) ? null : contactRepository.save(contact);
    }

    @Override
    public List<Contact> getContactsByName(String name) {
        List<Contact> contacts = contactRepository.findByNameContaining(name);
        return contacts;
    }

    @Override
    public List<Contact> getContactsByEmail(String email) {
        List<Contact> contacts = contactRepository.findByEmail(email);
        return contacts;
    }

    @Override
    public List<Contact> getContactsByProfession(String profession) {
        List<Contact> contacts = contactRepository.findByProfession(profession);
        return contacts;
    }

    @Override
    public List<Contact> getContactsByNameAndEmail(String name, String email) {
        List<Contact> contacts = contactRepository.findByNameAndEmail(name, email);
        return contacts;
    }

    @Override
    public List<Contact> getContactsByNameAndProfession(String name, String profession) {
        List<Contact> contacts = contactRepository.findByNameAndProfession(name, profession);
        return contacts;
    }

    @Override
    public List<Contact> getContactsByEmailAndProfession(String email, String profession) {
        List<Contact> contacts = contactRepository.findByEmailAndProfession(email, profession);
        return contacts;
    }

    @Override
    public List<Contact> getContactsByNameAndEmailAndProfession(String name, String email, String profession) {
        List<Contact> contacts = contactRepository.findByNameAndEmailAndProfession(name, email, profession);
        return contacts;
    }
}
