package com.ambition.prodyna.infrastructure.persistence.jpa.person;

import java.io.Serializable;
import java.util.UUID;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import com.ambition.prodyna.application.log.person.PersonChange;
import com.ambition.prodyna.application.log.person.PersonEvent;
import com.ambition.prodyna.application.security.PermissionException;
import com.ambition.prodyna.domain.model.person.Person;
import com.ambition.prodyna.domain.model.person.PersonRepository;

@Repository(forEntity = Person.class)
public abstract class PersonRepositoryImpl extends AbstractEntityRepository<Person, String> implements PersonRepository, Serializable {

    @Inject
    @PersonChange
    private Event<PersonEvent> personEvents;

    @Override
    public boolean create(final Person entity) throws PermissionException {
	final Person existCheck = this.read(entity.getUuid());
	if (existCheck != null) {
	    return existCheck.equals(entity);
	}
	this.save(entity);
	return true;
    }

    @Override
    public void delete(final UUID uuid) throws PermissionException {
	final Person existCheck = this.read(uuid);
	this.remove(existCheck);
    }

    @Override
    public Person read(final UUID uuid) throws PermissionException {
	final Person entity = this.findBy(uuid.toString());
	return entity;
    }

    @Override
    public void update(final Person entity) throws PermissionException {
	this.save(entity);
    }
}
