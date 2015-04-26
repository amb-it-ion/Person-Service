package com.ambition.prodyna.domain.model.person;

import java.util.UUID;

import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Person{

    public String name;

    @Id
    private String uuid;

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || this.getClass() != o.getClass()) {
	    return false;
	}
	final Person event = (Person) o;
	return this.samePersonAs(event);
    }

    public UUID getUuid() {
	return UUID.fromString( this.uuid );
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(this.uuid).append(this.name)
		.toHashCode();
    }

    private boolean samePersonAs(final Person other) {
	return other != null
		&& new EqualsBuilder().append(this.name, other.name)
		.append(this.uuid, other.uuid).isEquals();
    }

    public void setUuid( final UUID uuid ) {
	this.uuid = uuid.toString();
    }
}
