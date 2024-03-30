package com.zyris.calorisecalculator.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Product {
    private Integer id;
    private String productName;
    private BigDecimal calories;
    private BigDecimal n;
    private BigDecimal f;
    private BigDecimal c;
    private String description;
}
