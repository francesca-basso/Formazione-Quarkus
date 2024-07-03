package org.acme.services;

import org.acme.entity.Person;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PersonService {

    @PersistenceContext
    public EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Person createPerson(String name) {
        Person person = new Person(name);
        entityManager.persist(person);
        return person;
    }

    public Person findPersonById(Long id) {
        return entityManager.find(Person.class, id);
    }
}
