package com.company.model.artificial_intelligence;

import com.company.model.board.Board;
import com.company.model.board.Way;
import com.company.model.danger.Danger;
import com.company.model.figure.FigureColor;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.PrintUtil;

import static org.junit.jupiter.api.Assertions.*;

class BasicAiTest {

    @Test
    void getBestMove() {
    }

    @Test
    void test() {
        FigureColor myColor = FigureColor.WHITE;
        Board board = BoardUtil.boardOf("pbb3, qwe3, nwd5, qba7, kbe7, pwf6, kwf2");
        Danger danger = new Danger(board, myColor);
        PrintUtil.printPrimitiveBoard(board);
        PrintUtil.print(danger);
        PrintUtil.printBoard(board);

        Iai ai = new BasicAi(board);
        Way way = ai.getBestMove(danger, myColor);
        board.transfer(way);
        PrintUtil.printBoard(board);

    }

}