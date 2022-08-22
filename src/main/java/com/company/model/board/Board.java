package com.company.model.board;

import com.company.model.unit.UnitNull;
import com.company.model.unit.Unit;

public class Board {
    public static final int SIZE = 8;
    private final Unit[][] array = new Unit[SIZE][SIZE];

    public Board() {
        clear();
    }

    private void clear() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = UnitNull.getInstance();
            }
        }
    }

    public void insert(Unit unit, String position) {
        Cell cell = toCell(position);
        insert(unit, cell);
    }

    public void insert(Unit unit, Cell cell) {
        array[cell.row][cell.column] = unit;
    }

    public Unit remove(Cell cell) {
        Unit out = array[cell.row][cell.column];
        array[cell.row][cell.column] = UnitNull.getInstance();
        return out;
    }

    public Unit remove(String position) {
        Cell cell = toCell(position);
        return remove(cell);
    }

    public static Cell toCell(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException(String.format("incorrect length string position: %d, may be 2", position.length()));
        }
        int column = position.toLowerCase().charAt(0) - 'a';
        int row = SIZE - (position.charAt(1) - '0');
        verifyCoordinateLimit(column, row);
        return new Cell(column, row);
    }

    public static String toPosition(Cell cell) {
        int column = cell.column;
        int row = cell.row;

        verifyCoordinateLimit(column, row);
        char[] arr = new char[] {'a', '0'};
        arr[0] += column;
        arr[1] += SIZE - row;
        return String.valueOf(arr);
    }

    private static void verifyCoordinateLimit(int... coordinates) {
        for (int n : coordinates) {
            if (n < 0 || n >= SIZE) {
                throw new IllegalArgumentException(String.format("incorrect coordinate number: %d, may be 0... %d ", n, SIZE - 1));
            }
        }
    }


    public Unit get(int row, int column) {
        return array[row][column];
    }


    public Unit get(Cell cell) {
        return get(cell.row, cell.column);
    }

    public Unit get(String position) {
        Cell cell = toCell(position);
        return get(cell);
    }

    public boolean isCorrect(Cell cell) {
        return cell.column >= 0 && cell.column < SIZE && cell.row >= 0 && cell.row < SIZE;
    }

    public boolean isCorrect(String position) {
        try {
            Cell cell = toCell(position);
            return isCorrect(cell);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }



}
