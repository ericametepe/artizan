package com.keya.demoelastic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JobberAddRequestDTO {
    private String fname;
    private String lname;
    private String email;
}
