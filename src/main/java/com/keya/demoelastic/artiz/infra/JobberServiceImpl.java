package com.keya.demoelastic.artiz.infra;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobberServiceImpl implements JobberService {

    private final JobberRepository jobberRepository;

    @Override
    public Jobber save(Jobber jobber) {
        return jobberRepository.save(jobber);
    }

    @Override
    public List<Jobber> getAll() {

        return Lists.newArrayList(jobberRepository.findAll());
    }
}
