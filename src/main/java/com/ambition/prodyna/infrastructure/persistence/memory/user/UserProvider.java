package com.ambition.prodyna.infrastructure.persistence.memory.user;

import java.security.Principal;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.ambition.prodyna.infrastructure.persistence.memory.PermissionsRealm;
import com.ambition.prodyna.infrastructure.persistence.memory.user.impl.UserImpl;

public class UserProvider {

    @Inject
    Principal principal;

    @Inject
    PermissionsRealm realm;

    @Produces
    public User fetch(){
	final User user = new UserImpl(this.principal.getName());
	user.setPermissions(this.realm.getPermissionForPrincipal(this.principal.getName()));
	return user;
    }
}
