package com.company.service.danger;

import com.company.model.board.Cell;

public class DangerHelper {
    private DangerHelper() {
    }

    public static boolean check(boolean[][] array, Cell cell) {
        return array[cell.row][cell.column];
    }

}
