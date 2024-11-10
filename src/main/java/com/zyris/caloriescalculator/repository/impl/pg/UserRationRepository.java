package com.zyris.caloriescalculator.repository.impl.pg;

import com.zyris.caloriescalculator.domain.dao.UserRation;
import com.zyris.caloriescalculator.persistance.entity.UserRationPostgresEntity;
import com.zyris.caloriescalculator.persistance.entity.keys.UserRationKey;
import com.zyris.caloriescalculator.repository.UserRationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRationRepository extends JpaRepository<UserRationPostgresEntity, UserRationKey>, UserRationDAO {
    Optional<UserRationPostgresEntity> findByIdProductAndDay(Integer partOfName, LocalDate day);

//    @Override
//    Optional<List<UserRationPostgresEntity>> findById(UserRationKey userRationKey);
//    List<UserRationPostgresEntity> findByKeyIdPersonAndKeyDay(Long idPerson, LocalDate day);

    @Query("SELECT u FROM UserRationPostgresEntity u WHERE u.idPerson = :idPerson AND u.day = :day")
    List<UserRationPostgresEntity> findByKeyIdPersonAndKeyDay(@Param("idPerson") Long idPerson, @Param("day") LocalDate day);

    default List<UserRation> findByUserIdAndDay(Long idPerson, LocalDate day) {
        return findByKeyIdPersonAndKeyDay(idPerson, day)
                .stream()
                .map(ration -> UserRation.builder()
                        .idPerson(ration.getIdPerson())
                        .day(ration.getDay())
                        .idProduct(ration.getIdProduct())
                        .weight(ration.getWeight())
                        .build()
                )
                .toList();
    }

    default Optional<UserRation> findUserRationProductIdInSpecificDay(Integer productId, LocalDate day) {
        return findByIdProductAndDay(productId, day)
                .map(ration -> UserRation.builder()
                        .idPerson(ration.getIdPerson())
                        .day(ration.getDay())
                        .idProduct(ration.getIdProduct())
                        .weight(ration.getWeight())
                        .build()
                );
    }

    default UserRation save(UserRation userRation) {
        UserRationPostgresEntity savedRation = save(new UserRationPostgresEntity()

                        .setIdPerson(userRation.getIdPerson())
                        .setDay(userRation.getDay())
                        .setIdProduct(userRation.getIdProduct())

                .setWeight(userRation.getWeight())
        );
        return UserRation.builder()
                .idPerson(savedRation.getIdPerson())
                .day(savedRation.getDay())
                .idProduct(savedRation.getIdProduct())
                .weight(savedRation.getWeight())
                .build();
    }


}
