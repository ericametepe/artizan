package com.keya.demoelastic.artiz.infra;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

<<<<<<< HEAD
=======
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
>>>>>>> af5cb26e20d1bdaaadc18d16676429ef37e81df9
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Document(indexName = "artisan", type = "subscriber")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class SubscriberEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String firstName;
    @Field(type = FieldType.Text)
    private String city;
    @Field(type = FieldType.Text)
    private String lastName;
    @Field(type = FieldType.Text)
    private String email;
    @Field(type = FieldType.Text)
    @Size(min=8,max=16)
    private String password;
    @Field(type = FieldType.Text)
    private String role;
    @Field(type = FieldType.Boolean)
    private Boolean termsAndConditions;
    @Field(type = FieldType.Boolean)
    private boolean accountNonLocked;
    @Field(type = FieldType.Boolean)
    private boolean accountNonExpired;
    @Field(type = FieldType.Boolean)
    private boolean enabled;
    @Field(type = FieldType.Boolean)
    private boolean credentialsNonExpired;
    @Field(type = FieldType.Date)
    private LocalDateTime subscriptionDate;


}
