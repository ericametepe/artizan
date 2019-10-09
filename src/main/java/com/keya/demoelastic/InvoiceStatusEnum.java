package com.keya.demoelastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InvoiceStatusEnum {
    NEW("01"),
    VALID("02");
    private String code;
}
