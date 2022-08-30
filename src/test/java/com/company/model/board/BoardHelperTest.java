package com.company.model.board;

import com.company.model.piece.figure.Rank;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class BoardHelperTest {

    @Test
    void isObstacleOnLineFalse() {

        Triple[] test = new Triple[]{
                Triple.of("kbe5, rwb5"),
                Triple.of("kbe5, pwe2"),
                Triple.of("kbe5, nbh5"),
                Triple.of("kbe5, bwe8"),
                Triple.of("kbe5, bwe6"),
        };

        for (Triple t : test) {
            String string = (String) t.first;
            Board board = BoardUtil.boardOf(string);
            String[] array = getPositions(string);
            boolean actual = BoardHelper.isObstacleOnLine(board, array[0], array[1]);
            assertFalse(actual);
        }
    }

    @Test
    void isObstacleOnLineTrue() {

        Triple[] test = new Triple[]{
                Triple.of("kbe5, rwb5, pwc5"),
                Triple.of("kbe5, pwe2, pwe4"),
                Triple.of("kbe5, nbh5, pwg5"),
                Triple.of("kbe5, bwe8, pwe7"),
        };

        for (Triple t : test) {
            String string = (String) t.first;
            Board board = BoardUtil.boardOf(string);
            String[] array = getPositions(string);
            boolean actual = BoardHelper.isObstacleOnLine(board, array[0], array[1]);
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