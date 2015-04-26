package com.ambition.prodyna.domain.model.person;

import java.util.UUID;

public interface PersonRepository{

    public boolean create(final Person entity);

    public void delete(final UUID uuid);

    public Person read(final UUID uuid);

    public void update(final Person entity);
}
