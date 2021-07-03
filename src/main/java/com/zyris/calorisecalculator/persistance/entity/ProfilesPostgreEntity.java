package com.zyris.calorisecalculator.persistance.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
@Builder
@NoArgsConstructor
@Data
public class ProfilesPostgreEntity {
    @Id
    @Column(name = "id_person")
    private Integer idPerson;
    @Column(name = "id_chat")
    private Long idChat;

    public ProfilesPostgreEntity(Integer idPerson, Long idChat) {
        this.idPerson = idPerson;
        this.idChat = idChat;
    }
}
