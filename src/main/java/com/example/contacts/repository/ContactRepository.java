package com.example.contacts.repository;

import com.example.contacts.domain.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
    Contact findByName(@Param("name") String name);

    Contact findByEmail(@Param("email") String email);

    List<Contact> findByProfession(@Param("profession") String profession);

    Contact findById(@Param("id") Integer id);
}
