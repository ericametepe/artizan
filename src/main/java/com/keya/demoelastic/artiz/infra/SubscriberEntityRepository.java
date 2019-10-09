package com.keya.demoelastic.artiz.infra;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SubscriberEntityRepository extends ElasticsearchRepository<SubscriberEntity,String> {
}
