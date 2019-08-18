package com.keya.demoelastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//https://docs.spring.io/spring-data/elasticsearch/docs/current-SNAPSHOT/reference/html/#reference
@SpringBootApplication
@EnableElasticsearchRepositories
public class DemoElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoElasticApplication.class, args);
	}

}
