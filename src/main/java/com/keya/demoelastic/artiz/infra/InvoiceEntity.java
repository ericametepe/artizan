package com.keya.demoelastic.artiz.infra;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Document(indexName = "finance-idx" ,type = "invoice")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class InvoiceEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String numerator;
    private Integer month;
    @Field(type = FieldType.Integer)
    private Integer year;
    @Field(type = FieldType.Double)
    private BigDecimal workeddays;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Double)
    private BigDecimal dailywage;
    @Field(type = FieldType.Text)
    private String status;
    @Field(type = FieldType.Text)
    private String customerName;
    @Field(type = FieldType.Text)
    private String customerAdress;
    @Field(type = FieldType.Text)
    private String customerVatnumber;
    @Field(type=FieldType.Object)
    private CustomerEntity customerInfo;




}
