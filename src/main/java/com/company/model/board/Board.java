package com.company.model.board;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureNull;
import com.company.model.figure.FigureWithStatistic;

public class Board {
    public static final int SIZE = 8;
    private final FigureWithStatistic[][] array = new FigureWithStatistic[SIZE][SIZE];

    public Board() {
        clear();
    }

    private void clear() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = FigureNull.getInstance();
            }
        }
    }

    public void insert(FigureWithStatistic figure, String position) {
        Cell cell = toCell(position);
        insert(figure, cell);
    }

    public void insert(FigureWithStatistic figure, Cell cell) {
        array[cell.row][cell.column] = figure;
    }

    public FigureWithStatistic remove(Cell cell) {
        FigureWithStatistic out = array[cell.row][cell.column];
        array[cell.row][cell.column] = FigureNull.getInstance();
        return out;
    }

    public FigureWithStatistic remove(String position) {
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


    public FigureWithStatistic get(int row, int column) {
        return array[row][column];
    }


    public FigureWithStatistic get(Cell cell) {
        return get(cell.row, cell.column);
    }

    public FigureWithStatistic get(String position) {
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
