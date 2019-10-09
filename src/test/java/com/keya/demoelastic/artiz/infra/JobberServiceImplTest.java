package com.keya.demoelastic.artiz.infra;

import com.keya.demoelastic.ArtizElasticApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtizElasticApplication.class)
public class JobberServiceImplTest {
@Autowired
    private JobberService jobberService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Jobber.class);
        esTemplate.createIndex(Jobber.class);
        esTemplate.putMapping(Jobber.class);
        esTemplate.refresh(Jobber.class);
    }

    @Test
    public void save() {
        Jobber jobber = Jobber.builder().id(UUID.randomUUID().toString()).lname("yes u try ").build();
        Jobber jobber1 =jobberService.save(jobber);
        assertNotNull(jobber1);
    }
}