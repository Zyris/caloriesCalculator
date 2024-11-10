package com.zyris.caloriescalculator.repository;

import com.zyris.caloriescalculator.domain.dao.Product;

import java.util.List;

public interface DictionaryDAO {
    List<Product> findProductByNamePart(String partOfName);
}
