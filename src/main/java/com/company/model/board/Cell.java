package com.company.model.board;

import com.company.model.coordinate.Coordinate;

public class Cell extends Coordinate {
    public Cell(int column, int row) {
        super(column, row);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
