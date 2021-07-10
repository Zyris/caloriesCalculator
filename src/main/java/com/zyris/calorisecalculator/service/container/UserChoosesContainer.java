package com.zyris.calorisecalculator.service.container;

import com.zyris.calorisecalculator.domain.UserState;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class UserChoosesContainer {
    Map<Integer, UserState> userIdToUserStateMap = new HashMap<>();
}
