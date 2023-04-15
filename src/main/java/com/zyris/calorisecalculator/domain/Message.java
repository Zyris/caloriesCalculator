package com.zyris.calorisecalculator.domain;

import com.zyris.calorisecalculator.exception.IllegalArgumentException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class Message {
    private String productName;
    private Integer weight;

    public static Message from(String message) {
        List<String> splittedText = List.of(message.trim().split(" "));
        if (splittedText.size() < 2) {
            throw new IllegalArgumentException("product + weight, must be specified!");//todo rewrite message
        }
        return new Message()
                .setProductName(splittedText.get(0))
                .setWeight(Optional.ofNullable(splittedText.get(1))
                        .map(Integer::valueOf)
                        .orElse(null)
                );
    }
}
