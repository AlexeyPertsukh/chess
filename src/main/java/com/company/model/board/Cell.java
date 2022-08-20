package com.company.model.board;

public class Cell {
    public final int column;
    public final int row;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null ) {
            return false;
        }
        if(!(o instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) o;
        return column == other.column && row == other.row;
    }
}
