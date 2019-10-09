package com.keya.demoelastic.artiz.domaine;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Subscriber {
    private String id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String city;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min=8,max=16)
    private String password;
    private String role;
    @AssertTrue
    private Boolean termsAndConditions;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private LocalDateTime subscriptionDate;

}
