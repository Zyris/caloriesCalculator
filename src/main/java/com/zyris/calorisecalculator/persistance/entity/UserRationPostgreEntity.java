package com.zyris.calorisecalculator.persistance.entity;

import com.zyris.calorisecalculator.persistance.entity.keys.UserRationKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "user_ration")
@Data
@Accessors(chain = true)
public class UserRationPostgreEntity {
    @EmbeddedId
    private UserRationKey key;
    @Column(name = "id_product")
    private Integer idProduct;
    private Integer weight;

}
