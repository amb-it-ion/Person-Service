package com.ambition.prodyna.application.log.person;


public class PersonEvent {

    private final ChangeType changeType;
    private final Object message;

    public PersonEvent(final ChangeType changeType, final Object message) {
	super();
	this.changeType = changeType;
	this.message = message;
    }

    public ChangeType getChangeType() {
	return this.changeType;
    }

    public Object getMessage() {
	return this.message;
    }
}
