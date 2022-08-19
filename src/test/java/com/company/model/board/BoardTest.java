package com.company.model.board;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @org.junit.jupiter.api.Test
    void insert() {
    }

    @org.junit.jupiter.api.Test
    void remove() {
    }

    @org.junit.jupiter.api.Test
    void testRemove() {
    }

    @org.junit.jupiter.api.Test
    void get() {
    }

    @org.junit.jupiter.api.Test
    void testGet() {
    }

    @org.junit.jupiter.api.Test
    void isCorrect() {
    }

    @org.junit.jupiter.api.Test
    void testIsCorrect() {
    }

    private static final Pair[] arrayTestToPosToCoor = new Pair[]{
            new Pair("e2", "4, 6"),
            new Pair("h6", "7, 2"),
    };

//    @org.junit.jupiter.api.Test
//    void testToPosition() {
//        Board board = new Board();
//
//        Pair[] test = arrayTestToPosToCoor;
//
//        for (Pair p : test) {
//            String[] strings = ((String) p.getValue()).replace(" ", "").split(",");
//            int[] coors = toIntArray(strings);
//            String actual = board.toPosition(new Cell(coors[0], coors[1]));
//            String expected = (String) p.getKey();
//
//            assertEquals(expected, actual);
//        }
//    }

    @org.junit.jupiter.api.Test
    void testToCoordinates() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Pair[] test = arrayTestToPosToCoor;

        Board board = new Board();

        for (Pair p : test) {
            String[] strings = ((String) p.getValue()).replace(" ", "").split(",");
            int[] expected = toIntArray(strings);
            String arg = (String) p.getKey();
            Cell cell = board.toCell(arg);
            int[] actual = new int[] {cell.column, cell.row};

            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], actual[i]);
            }
        }
    }

    private static int[] toIntArray(String[] strings) {
        int[] out = new int[strings.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = Integer.parseInt(strings[i]);
        }
        return out;
    }

    /*
    Class<? extends Car> carClass = car.getClass();

Method startEngineMethod = carClass.getDeclaredMethod("startEngine");
     */
}