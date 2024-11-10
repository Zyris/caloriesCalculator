package com.zyris.caloriescalculator.repository.impl.pg;

import com.zyris.caloriescalculator.domain.dao.Product;
import com.zyris.caloriescalculator.persistance.entity.DictionaryPostgreEntity;
import com.zyris.caloriescalculator.repository.DictionaryDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

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

    @Override
    default List<Product> findAllByIds(Set<Integer> ids) {
        return findAllById(ids)
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
