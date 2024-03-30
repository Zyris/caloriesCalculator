package com.zyris.calorisecalculator.dao;

import com.zyris.calorisecalculator.domain.Product;

import java.util.List;

public interface DictionaryDAO {
    List<Product> findProductByNamePart(String partOfName);
}
