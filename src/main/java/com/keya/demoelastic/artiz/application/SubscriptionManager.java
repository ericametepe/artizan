package com.keya.demoelastic.artiz.application;

import com.keya.demoelastic.artiz.domaine.Subscriber;

import java.util.List;

public interface SubscriptionManager {
    Subscriber create(Subscriber suscriber);

    List<Subscriber> findAll();

}
