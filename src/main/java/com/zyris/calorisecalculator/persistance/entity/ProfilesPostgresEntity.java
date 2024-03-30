package com.zyris.calorisecalculator.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Builder
@NoArgsConstructor
@Data
public class ProfilesPostgresEntity {
    @Id
    @Column(name = "id_person")
    private Long idPerson;
    @Column(name = "id_chat")
    private Long idChat;

    public ProfilesPostgresEntity(Long idPerson, Long idChat) {
        this.idPerson = idPerson;
        this.idChat = idChat;
    }
}
