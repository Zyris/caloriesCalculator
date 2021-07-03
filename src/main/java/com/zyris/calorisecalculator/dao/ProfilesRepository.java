package com.zyris.calorisecalculator.dao;

import com.zyris.calorisecalculator.persistance.entity.DictionaryPostgreEntity;
import com.zyris.calorisecalculator.persistance.entity.ProfilesPostgreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<ProfilesPostgreEntity, Integer> {
}
