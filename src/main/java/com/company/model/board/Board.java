package com.company.model.board;

import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureRank;
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

    private void insert(Unit unit, Cell cell) {
        array[cell.row][cell.column] = unit;
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

    public boolean isCorrect(Cell cell) {
        return cell.column >= 0 && cell.column < SIZE && cell.row >= 0 && cell.row < SIZE;
    }

    public Unit transfer(Cell from, Cell to) {
        Unit unit = remove(from);
        insert(unit, to);
        return unit;
    }

    private Unit remove(Cell cell) {
        Unit out = array[cell.row][cell.column];
        array[cell.row][cell.column] = UnitNull.getInstance();
        return out;
    }

    public Cell find(FigureRank rank, FigureColor color) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Unit unit = get(i, j);
                if(!unit.isNull() && unit.getRank() == rank && unit.getColor() == color) {
                    return new Cell(j, i);
                }

            }
        }

        String message = String.format("%s %s not found on board", color.name().toLowerCase(), rank.name().toLowerCase());
        throw new IllegalArgumentException(message);
    }



}
