package com.ambition.prodyna.application.security;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class PermissionException extends SecurityException {
    public PermissionException(final String string) {
	super(string);
    }
}
