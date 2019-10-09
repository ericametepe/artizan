package com.keya.demoelastic.artiz.domaine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RoleEnum {
    SUBSCRIBER("00","Subscriber"),
    CUSTOMER("01","Customer"),
    JOBBER("02","Jobber");
    private String code;
    private String label;

    public static String getLabelByCode(String code) {
        return EnumSet.allOf(RoleEnum.class).stream().filter(roleEnum -> roleEnum.code.equalsIgnoreCase(code)).map(RoleEnum::getCode)
                .findFirst().orElseThrow(()->  new IllegalArgumentException(" "+code) );
    }
}
