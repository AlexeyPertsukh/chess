package com.company.board;

import com.company.figure.Figure;

public class Board {
    public static final int SIZE = 8;
    private final Figure[][] array = new Figure[SIZE][SIZE];

    public Board() {
        clear();
    }

    private void clear() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = Figure.NULL;
            }
        }
    }

    public void insert(Figure figure, String position) {
        Cell cell = toCell(position);
        array[cell.row][cell.column] = figure;
    }

    public Figure remove(Cell cell) {
        Figure out = array[cell.row][cell.column];
        array[cell.row][cell.column] = Figure.NULL;
        return out;
    }

    public Figure remove(String position) {
        Cell cell = toCell(position);
        return remove(cell);
    }

    protected Cell toCell(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException(String.format("incorrect length string position: %d, may be 2", position.length()));
        }
        int column = position.toLowerCase().charAt(0) - 'a';
        int row = SIZE - (position.charAt(1) - '0');
        verifyCoordinateLimit(column, row);
        return new Cell(column, row);
    }

    protected String toPosition(int column, int row) {
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


    public Figure get(int row, int column) {
        return array[row][column];
    }


    public Figure get(Cell cell) {
        return get(cell.row, cell.column);
    }

    public Figure get(String position) {
        Cell cell = toCell(position);
        return get(cell);
    }

    public boolean isCorrect(int column, int row) {
        return column >= 0 && column < SIZE && row >= 0 && row < SIZE;
    }

    public boolean isCorrect(String position) {
        try {
            Cell cell = toCell(position);
            return isCorrect(cell.row, cell.column);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }



}
