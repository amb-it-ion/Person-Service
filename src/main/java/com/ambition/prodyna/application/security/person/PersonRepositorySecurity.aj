package com.ambition.prodyna.application.security.person;

import javax.interceptor.Interceptors;

import com.ambition.prodyna.application.security.AllowedTo;
import com.ambition.prodyna.application.security.Guard;
import com.ambition.prodyna.application.security.PermissionType;
import com.ambition.prodyna.domain.model.person.PersonRepository;

public aspect PersonRepositorySecurity {

    declare @type : PersonRepository+ : @Interceptors(Guard.class);
    declare @method : * PersonRepository+.create(..) : @AllowedTo({PermissionType.WRITE});
    declare @method : * PersonRepository+.delete(..) : @AllowedTo({PermissionType.WRITE});
    declare @method : * PersonRepository+.update(..) : @AllowedTo({PermissionType.WRITE});
    declare @method : * PersonRepository+.read(..) : @AllowedTo({PermissionType.READ});
}
