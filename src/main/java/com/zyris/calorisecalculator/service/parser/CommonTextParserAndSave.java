package com.zyris.calorisecalculator.service.parser;

import com.zyris.calorisecalculator.dao.DictionaryDAO;
import com.zyris.calorisecalculator.dao.UserRationDAO;
import com.zyris.calorisecalculator.domain.*;
import com.zyris.calorisecalculator.exception.ProductNotFoundException;
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

    public ResponseContext operateUserAndHisMessage(User user, TelegramMessage telegramMessage) {
        Message message = Message.from(telegramMessage.text());//todo refactoring

        List<Product> finedProductByPartOfName = dictionaryDAO.findProductByNamePart(message.getProductName());

        if (finedProductByPartOfName.isEmpty()) {
            throw new ProductNotFoundException("Nothing found by " + message.getProductName() + " keyword");
        }

        user.setOperationMap(
                IntStream.range(0, finedProductByPartOfName.size())
                        .boxed()
                        .collect(Collectors.toMap(num -> "/ch" + num,
                                        num -> () ->
                                                new OperationContext(checkAndSave(finedProductByPartOfName.get(num).getId(), message.getWeight(), user.getUserId()))
                                )
                        )
        );
        user.setStatus(User.Status.NEED_EXTRA_INFO);


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
