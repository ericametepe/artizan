package com.keya.demoelastic.artiz.infra;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "jobbing", type = "jobber")
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

