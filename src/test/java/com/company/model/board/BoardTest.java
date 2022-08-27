package com.company.model.board;

import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureRank;
import jdk.internal.util.xml.impl.Pair;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.three.Triple;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void toCell() {
    }

    @Test
    void toPosition() {
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void isCorrect() {
    }

    @Test
    void transfer() {
    }

    @Test
    void find() {
//        Triple[] test = new Triple[]{ Triple.of("qwd5, pwc5, rbb7, kwg5, pbb5, bbd7, qbg8")};

        FigureColor myColor = FigureColor.WHITE;
        FigureRank myRank = FigureRank.QUEEN;
        Board board = BoardUtil.getBoard("qwd5, pwc5, rbb7, kwg5, pbb5, bbd7, qbg8");

        Cell cell = board.find(myRank, myColor);
        System.out.println(Board.toPosition(cell));
    }
}