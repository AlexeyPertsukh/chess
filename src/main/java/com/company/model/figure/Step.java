package com.company.model.figure;

public class Step {

    private final int offsetColumn;
    private final int offsetRow;

    public Step(int offsetColumn, int offsetRow) {
        this.offsetColumn = offsetColumn;
        this.offsetRow = offsetRow;
    }

    public static Step of(int offsetX, int offsetY) {
        return new Step(offsetX, offsetY);
    }

    public int getOffsetColumn() {
        return offsetColumn;
    }

    public int getOffsetRow() {
        return offsetRow;
    }



}
