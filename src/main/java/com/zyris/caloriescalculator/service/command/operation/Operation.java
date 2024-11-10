package com.zyris.caloriescalculator.service.command.operation;

import com.zyris.caloriescalculator.domain.OperationContext;

@FunctionalInterface
public interface Operation {
    OperationContext operate();
}
