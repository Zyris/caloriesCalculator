package com.zyris.caloriescalculator.repository;

import com.zyris.caloriescalculator.domain.dao.UserRation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRationDAO {

    Optional<UserRation> findUserRationProductIdInSpecificDay(Integer productId, LocalDate day);

    UserRation save(UserRation userRation);

    List<UserRation> findByUserIdAndDay(Long idPerson, LocalDate day);
}
