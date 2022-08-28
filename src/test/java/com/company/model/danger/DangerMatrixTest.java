package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.figure.FigureColor;
import jdk.internal.util.xml.impl.Pair;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.PrintUtil;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class DangerMatrixTest {

    @Test
    void isCheckTrue() {
        FigureColor white = FigureColor.WHITE;
        FigureColor black = FigureColor.BLACK;

        Triple[] test = new Triple[]{
                Triple.of("pbb3, qwe3, nwd5, qba7, kbe7, pwf6, kwf2", white),
                Triple.of("pbb3, qwe3, nwd5, qba7, kbg7, pwf6, kwf2", white),
                Triple.of("pbb3, qwe3, nwd5, qba7, kbg7, pwf6, kwa2", black),
        };

        for (Triple t : test) {
            String arg = (String) t.first;
            FigureColor color = (FigureColor) t.second;

            Board board = BoardUtil.boardOf(arg);
            Danger danger = new Danger(board, color);
            boolean actual = danger.isCheck();
            assertTrue(actual);
        }
    }

    @Test
    void isCheckFalse() {
        FigureColor white = FigureColor.WHITE;
        FigureColor black = FigureColor.BLACK;

        Triple[] test = new Triple[]{
                Triple.of("pbb3, qwe3, nwd5, qba7, kbe7, pwf6, kwf2", black),
                Triple.of("pbb3, qwe3, nwd5, qba7, kbg7, pwf6, kwf2", black),
        };

        for (Triple t : test) {
            String arg = (String) t.first;
            FigureColor aggressorColor = (FigureColor) t.second;

            Board board = BoardUtil.boardOf(arg);
            Danger danger = new Danger(board, aggressorColor);
            boolean actual = danger.isCheck();
            assertFalse(actual);
        }
    }
    @Test
    void test1() {
        Board board = BoardUtil.boardOf("pbb3, qwe3, nwd5, qba7, kbe7, pwf6, kwf2");
        Danger danger = new Danger(board, FigureColor.WHITE);
        PrintUtil.printPrimitiveBoard(board);
        PrintUtil.printBoard(board);
        PrintUtil.print(danger);

    }

}