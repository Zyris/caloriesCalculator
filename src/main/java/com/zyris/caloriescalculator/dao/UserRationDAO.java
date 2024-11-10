package com.zyris.caloriescalculator.dao;

import com.zyris.caloriescalculator.domain.dao.UserRation;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRationDAO {

    Optional<UserRation> findUserRationProductIdInSpecificDay(Integer productId, LocalDate day);

    UserRation save(UserRation userRation);

    Optional<UserRation> findByUserIdAndDay(Long idPerson, LocalDate day);
}
