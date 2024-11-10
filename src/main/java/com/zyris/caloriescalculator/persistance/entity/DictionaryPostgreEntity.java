package com.zyris.caloriescalculator.persistance.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "dictionary")
@Data
public class DictionaryPostgreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    @Column
    private BigDecimal calories;
    @Column
    private BigDecimal n;
    @Column
    private BigDecimal f;
    @Column
    private BigDecimal c;
    @Column
    private String description;
}






