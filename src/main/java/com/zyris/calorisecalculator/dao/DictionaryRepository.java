package com.zyris.calorisecalculator.dao;

import com.zyris.calorisecalculator.persistance.entity.DictionaryPostgreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<DictionaryPostgreEntity, Integer> {
    List<DictionaryPostgreEntity> findByProductNameContainingIgnoreCase(String partOfName);
}
