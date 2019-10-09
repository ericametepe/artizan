package com.keya.demoelastic;

import com.keya.demoelastic.artiz.infra.Jobber;
import com.keya.demoelastic.artiz.infra.JobberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@AllArgsConstructor
public class JobberResource {

    private final JobberService jobberService;

    @PostMapping(path = "/jobber")
    public ResponseEntity<?> addJobber(@RequestBody JobberAddRequestDTO jobberAddRequestDTO ){
        ModelMapper modelMapper = new ModelMapper();
        Jobber jobber =modelMapper.map(jobberAddRequestDTO, Jobber.class);
        Jobber jobber1 =jobberService.save(jobber);
        log.info(" Data to save "+jobber1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/jobber/{id}")
    public ResponseEntity<?> getJobber(@PathVariable String id){
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping(path = "/jobbers")
    public ResponseEntity<?> getAllJobbers(){

        return new ResponseEntity<>(jobberService.getAll(),HttpStatus.OK);
    }
}
