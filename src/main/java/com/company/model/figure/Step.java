package com.company.model.figure;

public class Step {

    public int offsetColumn;
    public int offsetRow;

    public Step(int offsetColumn, int offsetRow) {
        this.offsetColumn = offsetColumn;
        this.offsetRow = offsetRow;
    }

    public static Step of(int offsetX, int offsetY) {
        return new Step(offsetX, offsetY);
    }

}
