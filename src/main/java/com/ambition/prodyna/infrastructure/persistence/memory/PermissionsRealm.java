package com.ambition.prodyna.infrastructure.persistence.memory;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

import com.ambition.prodyna.application.security.PermissionType;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PermissionsRealm {

    private Map<String,EnumSet<PermissionType>> users;

    public EnumSet<PermissionType> getPermissionForPrincipal(final String userName){
	final EnumSet<PermissionType> configuredPermissions = this.users.get(userName);
	if(configuredPermissions != null) {
	    return configuredPermissions;
	}
	else {
	    return EnumSet.noneOf(PermissionType.class);
	}
    }

    @PostConstruct
    public void populateRealm(){
	this.users = new HashMap<String, EnumSet<PermissionType>>();
	this.users.put("user", EnumSet.allOf(PermissionType.class));
	this.users.put("anonymous", EnumSet.of(PermissionType.READ));
    }
}
