package com.company.model.artificial_intelligence.board;

import com.company.model.board.Board;
import com.company.model.board.BoardHelper;
import org.junit.jupiter.api.Test;
import com.company.model.board.BoardFactory;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class BoardHelperTest {

    @Test
    void isObstacleLineFalse() {

        Triple[] test = new Triple[]{
                Triple.of("kbe5, rwb5"),
                Triple.of("kbe5, pwe2"),
                Triple.of("kbe5, nbh5"),
                Triple.of("kbe5, bwe8"),
                Triple.of("kbe5, bwe6"),
        };

        for (Triple t : test) {
            String string = (String) t.first;
            Board board = BoardFactory.createdOf(string);
            String[] array = getPositions(string);
            boolean actual = BoardHelper.isObstacleLine(board, array[0], array[1]);
            assertFalse(actual);
        }
    }

    @Test
    void isObstacleLineTrue() {

        Triple[] test = new Triple[]{
                Triple.of("kbe5, rwb5, pwc5"),
                Triple.of("kbe5, pwe2, pwe4"),
                Triple.of("kbe5, nbh5, pwg5"),
                Triple.of("kbe5, bwe8, pwe7"),
        };

        for (Triple t : test) {
            String string = (String) t.first;
            Board board = BoardFactory.createdOf(string);
            String[] array = getPositions(string);
            boolean actual = BoardHelper.isObstacleLine(board, array[0], array[1]);
            assertTrue(actual);
        }
    }

    private static String[] getPositions(String string) {
        String[] out = string.replace(" ", "").split(",");
        for (int i = 0; i < out.length; i++) {
            out[i] = out[i].substring(2);
        }
        return out;
    }

    @Test
    void isOneLine() {
    }
}