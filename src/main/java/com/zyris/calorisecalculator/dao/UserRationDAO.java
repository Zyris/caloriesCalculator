package com.zyris.calorisecalculator.dao;

import com.zyris.calorisecalculator.domain.UserRation;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRationDAO {

    Optional<UserRation> findUserRationProductIdInSpecificDay(Integer productId, LocalDate day);

    UserRation save(UserRation userRation);


}
