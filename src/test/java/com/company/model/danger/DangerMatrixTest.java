package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.figure.FigureColor;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.PrintUtil;

import static org.junit.jupiter.api.Assertions.*;

class DangerMatrixTest {

    @Test
    void isCheck() {
    }

    @Test
    void test1() {
        Board board = BoardUtil.boardOf("pbb3, qwe3, nwd5, qba7, kbe7, pwf6");
        Danger danger = new Danger(board, FigureColor.WHITE);
        DangerMatrix dangerMatrix = danger.toMatrix();
        PrintUtil.printPrimitiveBoard(board);
        PrintUtil.printBoard(board);
        PrintUtil.print(dangerMatrix);

    }

}