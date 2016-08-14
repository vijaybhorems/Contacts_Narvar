package com.example.contacts.rest.mediatypes;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.NotNull;

@Relation
public class Contact implements Identifiable<Integer> {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    private String profession;

    public Contact() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
