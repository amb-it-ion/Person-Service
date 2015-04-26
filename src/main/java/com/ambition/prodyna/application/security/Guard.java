package com.ambition.prodyna.application.security;

import java.lang.reflect.Method;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.ambition.prodyna.infrastructure.persistence.memory.user.User;

/**
 * Intercepts method calls with permission validation.
 */
@Interceptor
@AllowedTo({})
public class Guard {

    @Inject
    Instance<User> user;

    public Guard(){}

    boolean isAllowed(final Method method,final User u) {
	final AllowedTo annotation = method.getAnnotation(AllowedTo.class) == null ? method.getClass().getAnnotation(AllowedTo.class) : method.getAnnotation(AllowedTo.class);;
	if ( annotation == null) {
	    return true;
	}
	final PermissionType[] permissions = annotation.value();
	for (final PermissionType permission : permissions) {
	    if(u.isAllowed(permission)){
		return true;
	    }
	}
	return false;
    }

    @AroundInvoke
    public Object validatePermissions(final InvocationContext ic) throws Exception{
	final Method method = ic.getMethod();
	final User user = this.user.get();
	if(!this.isAllowed(method, user)){
	    throw new PermissionException("User " + user + " is not allowed to execute the method " + method);
	}
	return ic.proceed();
    }
}
