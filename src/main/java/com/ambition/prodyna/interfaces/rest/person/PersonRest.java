package com.ambition.prodyna.interfaces.rest.person;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ambition.prodyna.domain.model.person.Person;

/**
 * This defines our REST Transfer Object
 */
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PersonRest {

    @NotNull(message = "name must be provided")
    @Size.List({ @Size(min = 3, message = "name must be at least {min} characters"), @Size(max = 20, message = "name must be less than {max} characters") })
    private String name;

    @NotNull(message = "uuid must be provided")
    private String uuid;

    public String getName() {
	return this.name;
    }

    public String getUuid() {
	return this.uuid;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public void setUuid(final String uuid) {
	this.uuid = uuid;
    }

    public Person toPerson() {
	final Person result = new Person();
	result.setUuid(UUID.fromString(this.uuid));
	result.name = this.name;
	return result;
    }

}