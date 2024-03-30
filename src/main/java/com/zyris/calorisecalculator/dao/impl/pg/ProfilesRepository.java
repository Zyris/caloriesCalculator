package com.zyris.calorisecalculator.dao.impl.pg;

import com.zyris.calorisecalculator.dao.ProfilesDAO;
import com.zyris.calorisecalculator.domain.Profile;
import com.zyris.calorisecalculator.persistance.entity.ProfilesPostgresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<ProfilesPostgresEntity, Long>, ProfilesDAO {

    default Profile save(Profile profile) {
        ProfilesPostgresEntity savedDto = this.save(
                ProfilesPostgresEntity.builder()
                        .idPerson(profile.getIdPerson())
                        .idChat(profile.getIdChat())
                        .build()
        );
        return Profile.builder()
                .idPerson(savedDto.getIdPerson())
                .idChat(savedDto.getIdChat())
                .build();

    }

}
