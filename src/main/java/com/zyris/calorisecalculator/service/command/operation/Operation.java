package com.zyris.calorisecalculator.service.command.operation;

import com.zyris.calorisecalculator.domain.OperationContext;
import com.zyris.calorisecalculator.domain.ResponseContext;

@FunctionalInterface
public interface Operation {
    OperationContext operate();
}
