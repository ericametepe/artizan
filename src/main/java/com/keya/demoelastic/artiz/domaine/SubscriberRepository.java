package com.keya.demoelastic.artiz.domaine;

import com.keya.demoelastic.artiz.infra.SubscriberEntity;

import java.util.List;

public interface SubscriberRepository {
    Subscriber save(Subscriber subscriber);

    List<SubscriberEntity> findAll();
}
