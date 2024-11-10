package com.zyris.caloriescalculator.repository;

import com.zyris.caloriescalculator.domain.dao.Product;

import java.util.List;
import java.util.Set;

public interface DictionaryDAO {
    List<Product> findProductByNamePart(String partOfName);
    List<Product> findAllByIds(Set<Integer> ids);
}
