package com.company.model.coordinate;

import com.company.model.board.Cell;

public class Coordinate {
    public final int column;
    public final int row;

    public Coordinate(int column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null ) {
            return false;
        }
        if(!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate other = (Coordinate) o;
        return column == other.column && row == other.row;
    }
}
