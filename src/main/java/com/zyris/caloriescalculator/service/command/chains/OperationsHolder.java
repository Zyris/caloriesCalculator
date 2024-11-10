package com.zyris.caloriescalculator.service.command.chains;

import com.zyris.caloriescalculator.service.command.operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationsHolder {
    private List<Operation> operationList = new ArrayList<>();
    public OperationsHolder addOperation(Operation next) {
        this.operationList.add(next);
        return this;
    }

}
