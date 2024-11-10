package com.zyris.caloriescalculator.service.command.chains;

import com.zyris.caloriescalculator.service.command.operation.Operation;

public class OperationChainBuilder {
    private final OperationsHolder operationsHolder = new OperationsHolder();

    public OperationChainBuilder addOperation(Operation next) {
        this.operationsHolder.addOperation(next);
        return this;
    }

    public OperationsHolder build() {
        return this.operationsHolder;
    }
}
