package com.zyris.caloriescalculator.repository.impl.pg;

import com.zyris.caloriescalculator.domain.dao.UserRation;
import com.zyris.caloriescalculator.persistance.entity.UserRationPostgresEntity;
import com.zyris.caloriescalculator.persistance.entity.keys.UserRationKey;
import com.zyris.caloriescalculator.repository.UserRationDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRationRepository extends JpaRepository<UserRationPostgresEntity, UserRationKey>, UserRationDAO {
    Optional<UserRationPostgresEntity> findByIdProductAndKeyDay_Day(Integer partOfName, LocalDate day);

    @Override
    Optional<UserRationPostgresEntity> findById(UserRationKey userRationKey);

    default Optional<UserRation> findByUserIdAndDay(Long idPerson, LocalDate day) {
        return findById(new UserRationKey().setIdPerson(idPerson).setDay(day))
                .map(ration -> UserRation.builder()
                        .idPerson(ration.getKey().getIdPerson())
                        .day(ration.getKey().getDay())
                        .idProduct(ration.getIdProduct())
                        .weight(ration.getWeight())
                        .build()
                );
    }

    default Optional<UserRation> findUserRationProductIdInSpecificDay(Integer productId, LocalDate day) {
        return findByIdProductAndKeyDay_Day(productId, day)
                .map(ration -> UserRation.builder()
                        .idPerson(ration.getKey().getIdPerson())
                        .day(ration.getKey().getDay())
                        .idProduct(ration.getIdProduct())
                        .weight(ration.getWeight())
                        .build()
                );
    }

    default UserRation save(UserRation userRation) {
        UserRationPostgresEntity savedRation = save(new UserRationPostgresEntity()
                .setKey(new UserRationKey()
                        .setIdPerson(userRation.getIdPerson())
                        .setDay(userRation.getDay())
                )
                .setIdProduct(userRation.getIdProduct())
                .setWeight(userRation.getWeight())
        );
        return UserRation.builder()
                .idPerson(savedRation.getKey().getIdPerson())
                .day(savedRation.getKey().getDay())
                .idProduct(savedRation.getIdProduct())
                .weight(savedRation.getWeight())
                .build();
    }


}
