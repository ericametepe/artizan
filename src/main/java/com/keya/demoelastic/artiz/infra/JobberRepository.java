package com.keya.demoelastic.artiz.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface JobberRepository extends ElasticsearchRepository<Jobber, String> {
    Page<Jobber> findByFname(String fname, Pageable pageable);
}
