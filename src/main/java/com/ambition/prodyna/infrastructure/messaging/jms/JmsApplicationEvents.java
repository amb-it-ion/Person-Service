package com.ambition.prodyna.infrastructure.messaging.jms;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;

import com.ambition.prodyna.application.ApplicationEvents;
import com.ambition.prodyna.application.log.person.PersonChange;
import com.ambition.prodyna.application.log.person.PersonEvent;

@ApplicationScoped
public class JmsApplicationEvents implements Serializable, ApplicationEvents {

    private static final int LOW_PRIORITY = 0;

    @Inject
    JMSContext jmsContext;

    @Resource(lookup = "java:global/jms/personEventQueue")
    private Destination personEventQueue;

    /* (non-Javadoc)
     * @see com.ambition.prodyna.infrastructure.messaging.jms.ApplicationEvents#onPersonChanged(com.ambition.prodyna.application.log.person.PersonEvent)
     */
    @Override
    public void onPersonChanged(@Observes @PersonChange final PersonEvent personEvent) {
	this.jmsContext.createProducer()
	.setPriority(LOW_PRIORITY)
	.setDisableMessageID(true)
	.setDisableMessageTimestamp(true)
	.send(this.personEventQueue, personEvent.toString());
    }
}