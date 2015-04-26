package com.ambition.prodyna.infrastructure.persistence.jpa.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ambition.prodyna.domain.model.person.Person;

public aspect PersonPersistenceJPA {

    declare @field : * Person.name : @Column(name = "name",length=20);
    declare @field : * Person.uuid : @Column(name = "uuid");
    declare @type : Person : @Table(name = "person");
    declare @type : Person : @Entity;

}
