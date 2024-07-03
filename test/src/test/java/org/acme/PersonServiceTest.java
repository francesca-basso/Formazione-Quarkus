package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.acme.entity.Person;
import org.acme.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PersonServiceTest {

    private PersonService personService;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeEach
    public void setUp() {
        personService = new PersonService();

        // creare un mock dell'interfaccia entitymanager
        // stiamo simulando il comportamente senza dipendere da una reale connessione al db
        entityManager = mock(EntityManager.class);
        //assegna il mock entitymanager alla variabile entitymanager all'interno dell'istanza personService
        personService.entityManager = entityManager;

        //simuliamo il comportamento di entity transaction senza dipendere da una transazione reale
        /* transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction); */
    }

    @Test
    public void testFindPersonById() {
        // id e nome della persona che stiamo cercando nel test
        Long testId = 1L;
        String testName = "Jane Doe";
        // viene creato un oggetto person simulato
        Person expectedPerson = new Person(testId, testName);

        //specifichiamo il comportamento dell'entity manager quando viene chiamato il metodo find
        // quando il metodo find viene chiamato con questi parametri, Mockito restituirà expectedPerson
        when(entityManager.find(Person.class, testId)).thenReturn(expectedPerson);

        // Chiamiamo il metodo che dovrebbe usare l'entityManager mockato per trovare la persona con l'id testId
        Person foundPerson = personService.findPersonById(testId);

        // verifichiamo che il metodo find sia stato chiamato esattamente una volta con i paramteri specificiati
        verify(entityManager).find(Person.class, testId);

        // si verifica che non sia nullo
        assertNotNull(foundPerson);
        // si verifica che la persona abbia il nome corretto
        assertEquals(testName, foundPerson.getName());
    }
}
