package com.zyris.calorisecalculator.dao.impl.pg;

import com.zyris.calorisecalculator.dao.DictionaryDAO;
import com.zyris.calorisecalculator.domain.Product;
import com.zyris.calorisecalculator.persistance.entity.DictionaryPostgreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<DictionaryPostgreEntity, Integer>, DictionaryDAO {
    List<DictionaryPostgreEntity> findByProductNameContainingIgnoreCase(String partOfName);

    default List<Product> findProductByNamePart(String partOfName) {
        return findByProductNameContainingIgnoreCase(partOfName)
                .stream()
                .map(p -> Product.builder()
                        .id(p.getId())
                        .productName(p.getProductName())
                        .calories(p.getCalories())
                        .n(p.getN())
                        .f(p.getF())
                        .c(p.getC())
                        .description(p.getDescription())
                        .build()
                )
                .toList();
    }

}
