package com.company.model.danger;

import com.company.model.board.Cell;

import java.util.List;

public class DangerMatrix {
    private final boolean[][] array;
    private final List<Cell> checkList;

    public DangerMatrix(boolean[][] array, List<Cell> checkList) {
        this.array = array;
        this.checkList = checkList;
    }

    public boolean isUnderAttack(Cell cell) {
        return array[cell.row][cell.column];
    }

    public boolean[][] toArray() {
        return array.clone();
    }

    public List<Cell> getCheckList() {
        if(checkList == null) {
            String message = "missing attack list for king";
            throw new IllegalArgumentException(message);
        }
        return checkList;
    }

    public boolean isCheck() {
        return checkList != null;
    }

}
