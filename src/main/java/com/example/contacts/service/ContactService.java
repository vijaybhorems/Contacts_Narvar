package com.example.contacts.service;

import com.example.contacts.domain.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(Integer id);

    Contact updateContact(Contact contact);

    Contact getContactByName(String name);

    Contact getContactByEmail(String email);

    List<Contact> getContactsByProfession(String profession);
}
