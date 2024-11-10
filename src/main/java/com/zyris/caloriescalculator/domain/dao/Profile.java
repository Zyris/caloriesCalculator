package com.zyris.caloriescalculator.domain.dao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Profile {
    private Long idPerson;
    private Long idChat;
}
