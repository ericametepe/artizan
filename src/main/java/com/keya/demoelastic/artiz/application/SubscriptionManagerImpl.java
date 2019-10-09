package com.keya.demoelastic.artiz.application;

import com.keya.demoelastic.artiz.domaine.RoleEnum;
import com.keya.demoelastic.artiz.domaine.Subscriber;
import com.keya.demoelastic.artiz.infra.SubscriberEntity;
import com.keya.demoelastic.artiz.domaine.SubscriberRepository;
import com.sun.tools.javac.util.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionManagerImpl implements SubscriptionManager{

    private final SubscriberRepository subscriberRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Subscriber create(@Valid Subscriber suscriber) {

        Subscriber subscriberToSave=Subscriber.builder()
                .id(UUID.randomUUID().toString())
                .firstName(suscriber.getFirstName())
                .lastName(suscriber.getLastName())
                .email(StringUtils.toLowerCase(suscriber.getEmail()))
                .role(getRole(suscriber))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(false)
                .city(suscriber.getCity())
                .credentialsNonExpired(true)
                .termsAndConditions(suscriber.getTermsAndConditions())
                .password(passwordEncoder.encode(suscriber.getPassword()))
                .build();

        return subscriberRepository.save(subscriberToSave);
    }

    private String getRole(@Valid Subscriber suscriber) {
        return org.springframework.util.StringUtils.isEmpty(suscriber.getRole())? RoleEnum.SUBSCRIBER.getLabel():RoleEnum.getLabelByCode(suscriber.getRole());
    }

    @Override
    public List<Subscriber> findAll() {
        List<SubscriberEntity> subscriberEntities = subscriberRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
       List<Subscriber> subscribers = subscriberEntities.stream().map(subscriberEntity -> modelMapper.map(subscriberEntity,Subscriber.class)).collect(Collectors.toList());
        return subscribers;
    }
}
