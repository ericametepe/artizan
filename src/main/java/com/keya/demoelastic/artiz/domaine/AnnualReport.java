package com.keya.demoelastic.artiz.domaine;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class AnnualReport {
    private int year;
    private BigDecimal totalValue;
    private BigDecimal workeddays;
}
