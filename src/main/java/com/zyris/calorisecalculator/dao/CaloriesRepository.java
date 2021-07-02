package com.zyris.calorisecalculator.dao;

import com.zyris.calorisecalculator.persistance.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaloriesRepository extends JpaRepository<Dictionary, Integer> {

}
