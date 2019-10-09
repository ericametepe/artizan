package com.keya.demoelastic.artiz.domaine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    private String id;
    private String numerator;
    private Integer month;
    private Integer year;
    private BigDecimal workeddays;
    private Double price;
    private BigDecimal dailywage;
    private String status;
    private String customerName;
    private String customerAdress;
    private String customerVatnumber;
    private Customer customerInfo;
    private String description;
    private BigDecimal total;
    ;
}
