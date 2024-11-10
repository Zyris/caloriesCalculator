package com.zyris.caloriescalculator.dao.impl.pg;

import com.zyris.caloriescalculator.dao.ProfilesDAO;
import com.zyris.caloriescalculator.domain.dao.Profile;
import com.zyris.caloriescalculator.persistance.entity.ProfilesPostgresEntity;
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
