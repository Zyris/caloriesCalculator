package com.zyris.calorisecalculator.persistance.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dictionary")
@Data
public class Dictionary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "product_name")
    String productName;
    @Column
    BigDecimal calories;
    @Column
    BigDecimal n;
    @Column
    BigDecimal f;
    @Column
    BigDecimal c;
    @Column
    String description;
}






