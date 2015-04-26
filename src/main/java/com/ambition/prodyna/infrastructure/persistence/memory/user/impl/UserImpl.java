package com.ambition.prodyna.infrastructure.persistence.memory.user.impl;

import java.util.EnumSet;

import com.ambition.prodyna.application.security.PermissionType;
import com.ambition.prodyna.infrastructure.persistence.memory.user.User;

public class UserImpl implements User {

    private final String name;
    private EnumSet<PermissionType> permissions;

    public UserImpl(final String name) {
	this.name = name;
	this.permissions = EnumSet.noneOf(PermissionType.class);
    }

    @Override
    public void add(final PermissionType permission){
	this.permissions.add(permission);
    }

    @Override
    public boolean isAllowed(final PermissionType permission){
	return this.permissions.contains(permission);
    }

    @Override
    public void setPermissions(final EnumSet<PermissionType> permissionForPrincipal) {
	this.permissions = permissionForPrincipal;
    }

    @Override
    public String toString() {
	return "User{" + "name=" + this.name + ", permissions=" + this.permissions + '}';
    }
}
