package com.ambition.prodyna.infrastructure.persistence.person;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.enterprise.inject.Alternative;
import javax.ws.rs.Produces;

import com.ambition.prodyna.domain.model.person.Person;
import com.ambition.prodyna.domain.model.person.PersonRepository;

public class PersonRepositoryStub {

    @Produces
    @Alternative
    public PersonRepository createResource() {
	final PersonRepository personRepository = mock(PersonRepository.class);
	when(personRepository.create(any(Person.class))).thenReturn(true);
	return personRepository;
    }
}
