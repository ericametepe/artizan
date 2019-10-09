package com.keya.demoelastic.artiz.exposition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SubscriberPostDTO {
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String password;
    private String role;
    private Boolean termsAndConditions;
}
