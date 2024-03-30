package com.zyris.calorisecalculator.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Builder
@Data
@Accessors(chain = true)
public class UserRation {
    private Long idPerson;
    private LocalDate day;
    private Integer idProduct;
    private Integer weight;
}
