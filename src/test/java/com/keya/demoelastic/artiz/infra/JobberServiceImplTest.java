package com.keya.demoelastic.artiz.infra;

import com.keya.demoelastic.DemoElasticApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoElasticApplication.class)
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
        Jobber jobber = Jobber.builder().id("18097807").author("auth").build();
        Jobber jobber1 =jobberService.save(jobber);
        assertNotNull(jobber1.getAuthor());
    }
}