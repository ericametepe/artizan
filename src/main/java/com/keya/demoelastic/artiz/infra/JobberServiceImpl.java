package com.keya.demoelastic.artiz.infra;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobberServiceImpl implements JobberService {

    private JobberRepository jobberRepository;

    @Override
    public Jobber save(Jobber book) {
        return jobberRepository.save(book);
    }
}
