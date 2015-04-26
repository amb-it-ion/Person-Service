package com.ambition.prodyna.infrastructure.persistence.memory.user;

import java.util.EnumSet;

import com.ambition.prodyna.application.security.PermissionType;

public interface User {

    public abstract void add(PermissionType permission);

    public abstract boolean isAllowed(PermissionType permission);

    public abstract void setPermissions(EnumSet<PermissionType> permissionForPrincipal);

}