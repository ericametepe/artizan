package com.keya.demoelastic.artiz.exposition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubscriberGetDTO {
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String password;
    private String role;
    private Boolean termsAndConditions;
}
