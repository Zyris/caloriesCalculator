package com.zyris.calorisecalculator.dao;

import com.zyris.calorisecalculator.persistance.entity.DictionaryPostgreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaloriesRepository extends JpaRepository<DictionaryPostgreEntity, Integer> {

}
