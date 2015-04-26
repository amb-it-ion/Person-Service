package com.ambition.prodyna.application.monitor.person;

import com.ambition.prodyna.interfaces.rest.person.PersonEndpoint;
import net.anotheria.moskito.aop.annotation.Monitor;

public aspect PersonEndpointMonitor {

    declare @type : PersonEndpoint : @Monitor;
    
}
