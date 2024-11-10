package com.zyris.caloriescalculator.domain;

import com.zyris.caloriescalculator.exception.IllegalArgumentException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Message {
    private String productName;
    private Integer weight;

    public static Message from(String message) {
        List<String> splittedText = List.of(message.trim().split(" "));
        if (splittedText.size() != 2) {
            throw new IllegalArgumentException("must be specified in format: <product_name> <weight>");
        }
        return new Message()
                .setProductName(splittedText.get(0))
                .setWeight(Integer.valueOf(splittedText.get(1)));
    }
}
