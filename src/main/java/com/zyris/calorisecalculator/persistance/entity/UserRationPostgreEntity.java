package com.zyris.calorisecalculator.persistance.entity;

import com.zyris.calorisecalculator.persistance.entity.keys.UserRationKey;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
