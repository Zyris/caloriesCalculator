package com.zyris.caloriescalculator.service.command.impl;

import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.TelegramMessage;
import com.zyris.caloriescalculator.domain.dao.Product;
import com.zyris.caloriescalculator.domain.dao.UserRation;
import com.zyris.caloriescalculator.repository.UserRationDAO;
import com.zyris.caloriescalculator.repository.impl.pg.DictionaryRepository;
import com.zyris.caloriescalculator.service.command.CommandOperator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public class ShowStatisticCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/stat");
    private final UserRationDAO userRationDAO;
    private final DictionaryRepository dictionaryRepository;

    @Data
    @Accessors(chain = true)
    private class Summarizer {
        private static final BigDecimal DIVIDER_100 = BigDecimal.valueOf(100);
        private BigDecimal requiredCalories;
        private BigDecimal calories = BigDecimal.ZERO;
        private BigDecimal proteins = BigDecimal.ZERO;
        private BigDecimal fats = BigDecimal.ZERO;
        private BigDecimal carbs = BigDecimal.ZERO;

        public Summarizer merge(UserRation ration, Product product) {
            BigDecimal weight = BigDecimal.valueOf(ration.getWeight()).setScale(2,RoundingMode.HALF_UP).divide(DIVIDER_100, RoundingMode.HALF_UP);
            this.calories = this.calories.add(product.getCalories().multiply(weight));
            this.proteins = this.proteins.add(product.getN().multiply(weight));
            this.fats = this.fats.add(product.getF().multiply(weight));
            this.carbs = this.carbs.add(product.getC().multiply(weight));
            return this;
        }

        @Override
        public String toString() {
            BigDecimal sum = proteins.add(fats).add(carbs);
            return """
                    calories: %s (diff: %s, req:%s)
                    proteins: %s (%s)
                    fats: %s  (%s)
                    carbs: %s  (%s)
                    """.formatted(
                    calories, requiredCalories.subtract(calories), requiredCalories,
                    proteins, proteins.divide(sum,RoundingMode.HALF_UP),
                    fats, fats.divide(sum,RoundingMode.HALF_UP),
                    carbs, carbs.divide(sum,RoundingMode.HALF_UP)
            );
        }
    }

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public OperationContext apply(TelegramMessage telegramMessage) {
        Map<Integer, UserRation> idProductToRation = userRationDAO.findByUserIdAndDay(telegramMessage.userId(), LocalDate.now())
                .stream()
                .collect(toMap(UserRation::getIdProduct, s -> s));

        Map<Integer, Product> productToDict = dictionaryRepository.findAllByIds(idProductToRation.keySet())
                .stream()
                .collect(toMap(Product::getId, s -> s));

        Summarizer summary = idProductToRation
                .entrySet()
                .stream()
                .reduce(new Summarizer().setRequiredCalories(BigDecimal.valueOf(2050)),
                        (sum, ration) -> sum.merge(ration.getValue(), productToDict.get(ration.getKey())),
                        (s1, s2) -> s1);


        return new OperationContext(
                """
                        Statistic:
                        %s
                        """.formatted(summary));
    }

    @Override
    public String description() {
        return "Add eaten product";
    }
}
