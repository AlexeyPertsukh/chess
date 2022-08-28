package com.company.model.danger;

import com.company.model.board.Cell;

import java.util.List;

public class DangerMatrix {
    private final boolean[][] array;
    private final List<Danger.CheckList> checkLists;

    public DangerMatrix(boolean[][] array, List<Danger.CheckList> checkLists) {
        this.array = array;
        this.checkLists = checkLists;
    }

    public boolean isUnderAttack(Cell cell) {
        return array[cell.row][cell.column];
    }

    public boolean[][] toArray() {
        return array.clone();
    }

    public List<Danger.CheckList> getCheckLists() {
        return checkLists;
    }

    public boolean isCheck() {
        return !checkLists.isEmpty();
    }

}
