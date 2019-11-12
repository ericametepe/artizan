package com.keya.demoelastic.artiz.exposition;

<<<<<<< HEAD
import com.keya.demoelastic.artiz.application.SubscriptionManager;
import com.keya.demoelastic.artiz.domaine.Subscriber;
=======
import com.keya.demoelastic.artiz.domaine.Subscriber;
import com.keya.demoelastic.artiz.application.SubscriptionManager;
>>>>>>> af5cb26e20d1bdaaadc18d16676429ef37e81df9
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> af5cb26e20d1bdaaadc18d16676429ef37e81df9

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class SubscriptionResource {
    private final SubscriptionManager subscriptionManager;

    @PostMapping(value="/subscribers")
    public ResponseEntity<?> create(@RequestBody SubscriberPostDTO subscriberPostDTO){
        ModelMapper modelMapper = new ModelMapper();
        Subscriber suscriber = modelMapper.map(subscriberPostDTO,Subscriber.class);
        Subscriber subscriber= subscriptionManager.create(suscriber);
        SubscriberPostDTO subscriberResponseDTO = modelMapper.map(subscriber,SubscriberPostDTO.class);
        return new ResponseEntity<>(subscriberResponseDTO, HttpStatus.CREATED);

    }

    @GetMapping(value="/subscribers")
    public ResponseEntity<List<SubscriberGetDTO>> getAll(){
       List<Subscriber> subscribers = subscriptionManager.findAll();
       ModelMapper modelMapper = new ModelMapper();
       List<SubscriberGetDTO> subscriberDTOS =subscribers.stream().map(subscriber -> modelMapper.map(subscriber,SubscriberGetDTO.class)).collect(Collectors.toList());
        return  new ResponseEntity<>(subscriberDTOS, HttpStatus.OK);
    }

}
