package com.ambition.prodyna.application;

import com.ambition.prodyna.application.log.person.PersonEvent;

public interface ApplicationEvents {

    public abstract void onPersonChanged(PersonEvent personEvent);

}