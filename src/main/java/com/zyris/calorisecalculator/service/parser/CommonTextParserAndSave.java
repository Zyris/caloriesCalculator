package com.zyris.calorisecalculator.service.parser;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.dao.DictionaryRepository;
import com.zyris.calorisecalculator.dao.UserRationRepository;
import com.zyris.calorisecalculator.domain.UserState;
import com.zyris.calorisecalculator.exception.ProductNotFoundException;
import com.zyris.calorisecalculator.persistance.entity.DictionaryPostgreEntity;
import com.zyris.calorisecalculator.persistance.entity.UserRationPostgreEntity;
import com.zyris.calorisecalculator.persistance.entity.keys.UserRationKey;
import com.zyris.calorisecalculator.service.container.UserChoosesContainer;
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
    private final UserChoosesContainer userChoosesContainer;

    public String parseAndSave(Update update) {
        String text = update.message().text();

        String[] splitedText = text.trim().split(" ");
        String nameOfProduct = splitedText[0];
        Integer weight = Integer.valueOf(splitedText[1]);

        List<DictionaryPostgreEntity> finedProductByPartOfName = dictionaryRepository.findByProductNameContainingIgnoreCase(nameOfProduct);
        if (finedProductByPartOfName.size() == 0) {
            throw new ProductNotFoundException("Nothing found by " + nameOfProduct + " keyword");
        }
        //todo refactor
        Integer id = update.message().from().id();
        if (!userChoosesContainer.getUserIdToUserStateMap().containsKey(id)) {
            userChoosesContainer.getUserIdToUserStateMap().put(id, new UserState(id).setStatus(UserState.Status.NONE));
        }

        //todo refactor
        userChoosesContainer.getUserIdToUserStateMap().get(id).setOperationMap(
                IntStream.range(0, finedProductByPartOfName.size())
                        .boxed()
                        .collect(Collectors.toMap(num -> "/ch" + num,
                                num -> () ->
                                        checkAndSave(finedProductByPartOfName.get(num).getId(), weight, id)

                                )
                        )
        );
        userChoosesContainer.getUserIdToUserStateMap().get(id).setStatus(UserState.Status.NEED_EXTRA_INFO);



        //todo need refactor
        return IntStream.range(0, finedProductByPartOfName.size())
                .boxed()
                .map(number -> finedProductByPartOfName.get(number).getProductName() + " /ch" + number)
                .collect(Collectors.joining("\n"));


    }

    private String checkAndSave(Integer productId, Integer weight, Integer idPerson) {
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
