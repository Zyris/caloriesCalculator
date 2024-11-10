package com.zyris.caloriescalculator.persistance.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@Accessors(chain = true)
public class UserRationKey implements Serializable {
    @Column(name = "id_person")
    private Long idPerson;
    private LocalDate day;
    private Integer idProduct;
}
