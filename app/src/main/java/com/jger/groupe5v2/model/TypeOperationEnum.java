package com.jger.groupe5v2.model;

public enum TypeOperationEnum {
    ADD("+"),
    SUBSTRACT("-"),
    MULTIPLY("x"),
    DIVIDE("/");

    private final String symbol;

    TypeOperationEnum(String symbol) {
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
