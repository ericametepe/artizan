package com.keya.demoelastic.artiz.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface JobberRepository extends ElasticsearchRepository<Jobber, String> {
    Page<Jobber> findByAuthor(String author, Pageable pageable);
    List<Jobber> findByTitle(String title);
}
