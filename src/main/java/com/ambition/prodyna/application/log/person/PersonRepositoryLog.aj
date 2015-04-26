package com.ambition.prodyna.application.log.person;

import java.util.UUID;

import javax.inject.Inject;

import com.ambition.prodyna.domain.model.person.Person;
import com.ambition.prodyna.domain.model.person.PersonRepository;
import com.ambition.prodyna.application.ApplicationEvents;

public aspect PersonRepositoryLog {
    
    @Inject
    private ApplicationEvents applicationEvents;

    pointcut outerCreate(Person entity) : 
        execution(public void PersonRepository+.create(Person)) && 
        args(entity); 

    pointcut innerCreate() : call(* *.save(Person));

    after( Person entity ) : 
	cflow(outerCreate(entity)) &&
	innerCreate() {
		applicationEvents.onPersonChanged( new PersonEvent(ChangeType.CREATE, entity));
    }
    
    pointcut outerRead(UUID uuid) : 
        execution(public void PersonRepository+.read(UUID)) && 
        args(uuid); 

    pointcut innerRead() : call(* *.findBy(String));

    after( UUID uuid ) : 
	cflow(outerRead(uuid)) &&
	innerRead() {
		applicationEvents.onPersonChanged( new PersonEvent(ChangeType.READ, uuid));
    }
    
    pointcut outerDelete(UUID uuid) : 
        execution(public void PersonRepository+.delete(UUID)) && 
        args(uuid); 

    pointcut innerDelete() : call(* *.remove(Person));

    after( UUID uuid ) : 
	cflow(outerDelete(uuid)) &&
	innerDelete() {
		applicationEvents.onPersonChanged( new PersonEvent(ChangeType.DELETE, uuid));
    }
    
    pointcut outerUpdate(Person entity) : 
        execution(public void PersonRepository+.update(Person)) && 
        args(entity); 

    pointcut innerUpdate() : call(* *.update(Person));

    after( Person entity ) : 
	cflow(outerUpdate(entity)) &&
	innerUpdate() {
		applicationEvents.onPersonChanged( new PersonEvent(ChangeType.UPDATE, entity));
    }
}
