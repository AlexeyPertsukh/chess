package com.company.model.danger;

import com.company.model.board.Cell;

public class DangerMatrix {
    private final boolean[][] array;

    public DangerMatrix(boolean[][] array) {
        this.array = array;
    }

    public boolean isUnderAttack(Cell cell) {
        return array[cell.row][cell.column];
    }

    public boolean[][] toArray() {
        return array.clone();
    }

}
