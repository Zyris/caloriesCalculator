package com.zyris.caloriescalculator.service.parser;

import com.zyris.caloriescalculator.repository.DictionaryDAO;
import com.zyris.caloriescalculator.repository.UserModeDAO;
import com.zyris.caloriescalculator.repository.UserOptionsDAO;
import com.zyris.caloriescalculator.repository.UserRationDAO;
import com.zyris.caloriescalculator.domain.*;
import com.zyris.caloriescalculator.domain.dao.Product;
import com.zyris.caloriescalculator.domain.dao.UserRation;
import com.zyris.caloriescalculator.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CommonTextParserAndSave {
    private final DictionaryDAO dictionaryDAO;
    private final UserRationDAO userRationDAO;
    private final UserModeDAO userModeDAO;
    private final UserOptionsDAO userOptionsDAO;

    public ResponseContext operateUserAndHisMessage(TelegramMessage telegramMessage) {//todo need refactoring
        Message message = Message.from(telegramMessage.text());//todo refactoring

        List<Product> finedProductByPartOfName = dictionaryDAO.findProductByNamePart(message.getProductName());

        if (finedProductByPartOfName.isEmpty()) {
            throw new ProductNotFoundException("Nothing found by " + message.getProductName() + " keyword, try again");
        }

        userOptionsDAO.setUserOptions(telegramMessage.userId(),
                IntStream.range(0, finedProductByPartOfName.size())
                        .boxed()
                        .collect(Collectors.toMap(num -> "/ch" + num,
                                        num -> () ->
                                                new OperationContext(checkAndSave(finedProductByPartOfName.get(num).getId(), message.getWeight(), telegramMessage.userId()))
                                )
                        )
        );
        userModeDAO.setUserMode(telegramMessage.userId(), UserModeDAO.Mode.CHOOSE_OPTION);


        //todo need refactor
        return IntStream.range(0, finedProductByPartOfName.size())
                .boxed()
                .map(number -> finedProductByPartOfName.get(number).getProductName() + " /ch" + number)
                .collect(Collectors.collectingAndThen(Collectors.joining("\n"), ResponseContext::new));


    }

    private String checkAndSave(Integer productId, Integer weight, Long idPerson) {
        UserRation byIdProductAndDay = userRationDAO.findUserRationProductIdInSpecificDay(productId, LocalDate.now())
                .map(s -> s.setWeight(s.getWeight() + weight))
                .orElseGet(() -> UserRation.builder()
                        .day(LocalDate.now())
                        .idPerson(idPerson)
                        .idProduct(productId)
                        .weight(weight)
                        .build()
                );
        userRationDAO.save(byIdProductAndDay);

        return "saved";
    }
}
