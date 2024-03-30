package com.zyris.calorisecalculator.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Profile {
    private Long idPerson;
    private Long idChat;
}
