package com.zyris.calorisecalculator.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Message {
    private String productName;
    private Integer weight;

    public static Message from(String message) {
        String[] splittedText = message.trim().split(" ");
        Message obj = new Message();
        obj.setProductName(splittedText[0])
                .setWeight(Integer.valueOf(splittedText[1]));
        return obj;
    }
}
