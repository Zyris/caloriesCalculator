package com.zyris.calorisecalculator.domain;


import lombok.Data;

@Data
public class OperationContext {
    private final String message;
    private OperationStatus status = OperationStatus.CREATED;

    public OperationContext(String message) {
        this.message = message;
    }

    public enum OperationStatus {
        CREATED, DONE, FAILED
    }
}
