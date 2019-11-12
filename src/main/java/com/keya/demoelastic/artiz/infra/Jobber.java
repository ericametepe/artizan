package com.keya.demoelastic.artiz.infra;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

<<<<<<< HEAD
@Document(indexName = "jobbing", type = "jobber")
=======
@Document(indexName = "artisa", type = "jobber")
>>>>>>> af5cb26e20d1bdaaadc18d16676429ef37e81df9
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Jobber {
    @Id
    private String id;
    private String title;
    private String fname;
    private String lname;
    private String login;
    private String role;
}

