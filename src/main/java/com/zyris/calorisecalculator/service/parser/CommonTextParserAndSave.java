package com.zyris.calorisecalculator.service.parser;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.dao.DictionaryRepository;
import com.zyris.calorisecalculator.dao.UserRationRepository;
import com.zyris.calorisecalculator.domain.Message;
import com.zyris.calorisecalculator.domain.TelegramMessage;
import com.zyris.calorisecalculator.domain.User;
import com.zyris.calorisecalculator.exception.ProductNotFoundException;
import com.zyris.calorisecalculator.persistance.entity.DictionaryPostgreEntity;
import com.zyris.calorisecalculator.persistance.entity.UserRationPostgreEntity;
import com.zyris.calorisecalculator.persistance.entity.keys.UserRationKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CommonTextParserAndSave {
    private final DictionaryRepository dictionaryRepository;
    private final UserRationRepository userRationRepository;

    public String operateUserAndHisMessage(User user, TelegramMessage telegramMessage) {
        Message message = Message.from(telegramMessage.getText());//todo refactoring

        List<DictionaryPostgreEntity> finedProductByPartOfName = dictionaryRepository.findByProductNameContainingIgnoreCase(message.getProductName());

        if (finedProductByPartOfName.size() == 0) {
            throw new ProductNotFoundException("Nothing found by " + message.getProductName() + " keyword");
        }

        user.setOperationMap(
                IntStream.range(0, finedProductByPartOfName.size())
                        .boxed()
                        .collect(Collectors.toMap(num -> "/ch" + num,
                                num -> () ->
                                        checkAndSave(finedProductByPartOfName.get(num).getId(), message.getWeight(), user.getUserId())

                                )
                        )
        );
        user.setStatus(User.Status.NEED_EXTRA_INFO);


        //todo need refactor
        return IntStream.range(0, finedProductByPartOfName.size())
                .boxed()
                .map(number -> finedProductByPartOfName.get(number).getProductName() + " /ch" + number)
                .collect(Collectors.joining("\n"));


    }

    private String checkAndSave(Integer productId, Integer weight, Long idPerson) {
        UserRationPostgreEntity byIdProductAndDay = userRationRepository.findByIdProductAndKeyDay_Day(productId, LocalDate.now())
                .map(s -> s.setWeight(s.getWeight() + weight))
                .orElseGet(() -> new UserRationPostgreEntity()
                        .setKey(new UserRationKey().setDay(LocalDate.now()).setIdPerson(idPerson))
                        .setIdProduct(productId)
                        .setWeight(weight));
        userRationRepository.save(byIdProductAndDay);

        return "saved";
    }
}
