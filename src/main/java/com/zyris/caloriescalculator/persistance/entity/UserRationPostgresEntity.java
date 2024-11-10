package com.zyris.caloriescalculator.persistance.entity;

import com.zyris.caloriescalculator.persistance.entity.keys.UserRationKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Table(name = "user_ration")
@Data
@Accessors(chain = true)
public class UserRationPostgresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPerson;
    private LocalDate day;
    private Integer idProduct;
    private Integer weight;

}
