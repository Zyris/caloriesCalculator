package com.zyris.calorisecalculator.domain;

import com.zyris.calorisecalculator.service.operation.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class UserState {
    private final Integer userId;
    private Status status;
    private Map<String, Operation> operationMap;

    public enum Status {
        NONE, NEED_EXTRA_INFO;
    }

}
