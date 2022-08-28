package com.company.model.board;

import com.company.model.coordinate.Coordinate;

public class Cell extends Coordinate {
    public Cell(int column, int row) {
        super(column, row);
    }

    public Cell(Coordinate c) {
        this(c.row, c.column);
    }

    public Cell sum(Coordinate other) {
        return new Cell(column + other.column, row + other.row);
    }

    public Cell sum(int otherColumn, int otherRow) {
        return new Cell(column + otherColumn, row + otherRow);
    }

    public Cell updateRow(int update) {
        return new Cell(column, update);
    }

    public Cell updateColumn(int update) {
        return new Cell(update, row);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
