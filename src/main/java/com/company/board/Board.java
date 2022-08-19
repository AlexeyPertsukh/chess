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
        int[] coors = toCoordinates(position);
        array[coors[1]][coors[0]] = figure;
    }

    public Figure remove(int column, int row) {
        Figure out = array[row][column];
        array[row][column] = Figure.NULL;
        return out;
    }

    public Figure remove(String position) {
        int[] coors = toCoordinates(position);
        return remove(coors[0], coors[1]);
    }

    private int[] toCoordinates(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException(String.format("incorrect length string position: %d, may be 2", position.length()));
        }
        int column = position.toLowerCase().charAt(0) - 'a';
        int row = position.charAt(1) - '1';
        verifyCoordinateLimit(column, row);
        return new int[] {column, row};
    }

    private String toPosition(int column, int row) {
        verifyCoordinateLimit(column, row);
        char[] arr = new char[] {'a', '1'};
        arr[0] += column;
        arr[1] += row;
        return String.valueOf(arr);
    }

    private static void verifyCoordinateLimit(int... coordinates) {
        for (int n : coordinates) {
            if (n < 0 || n >= SIZE) {
                throw new IllegalArgumentException(String.format("incorrect coordinate number: %d, may be 0... %d ", n, SIZE - 1));
            }
        }
    }

    public Figure get(int column, int row) {
        return array[row][column];
    }

    public Figure get(String position) {
        int[] coors = toCoordinates(position);
        return get(coors[0], coors[1]);
    }

    public boolean isCorrect(int column, int row) {
        return column >= 0 && column < SIZE && row >= 0 && row < SIZE;
    }

    public boolean isCorrect(String position) {
        try {
            int[] coors = toCoordinates(position);
            return isCorrect(coors[0], coors[1]);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }



}
