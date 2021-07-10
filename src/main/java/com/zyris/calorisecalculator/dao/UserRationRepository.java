package com.zyris.calorisecalculator.dao;

import com.zyris.calorisecalculator.persistance.entity.UserRationPostgreEntity;
import com.zyris.calorisecalculator.persistance.entity.keys.UserRationKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRationRepository extends JpaRepository<UserRationPostgreEntity, UserRationKey> {
    Optional<UserRationPostgreEntity> findByIdProductAndKeyDay_Day(Integer partOfName, LocalDate day);


}
