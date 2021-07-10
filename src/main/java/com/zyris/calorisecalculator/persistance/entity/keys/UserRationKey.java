package com.zyris.calorisecalculator.persistance.entity.keys;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@Accessors(chain = true)
public class UserRationKey implements Serializable {
    @Column(name = "id_person")
    private Integer idPerson;
    private LocalDate day;
}
