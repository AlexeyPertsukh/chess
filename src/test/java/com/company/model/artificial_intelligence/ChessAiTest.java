package com.company.model.artificial_intelligence;

import com.company.model.board.Board;
import test_util.BoardUtil;
import com.company.model.danger.Danger;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import org.junit.jupiter.api.Test;
import test_util.PrintUtil;

import java.util.List;

class ChessAiTest {

    @Test
    void allPossibleMoves() {
        FigureColor myColor = FigureColor.WHITE;
        Board board = BoardUtil.boardOf("qwd5, pwc5, rbb7, kwg5, pbb5, bbd7, qbg8");

        ChessAi chessAi = new ChessAi(board);
        Danger danger = new Danger(board);
        DangerMatrix dangerMatrix = danger.toMatrix(FigureColor.BLACK);
        List<PossibleMove> list = chessAi.allPossibleMoves(myColor, dangerMatrix);

        PrintUtil.print(list);
        System.out.println();
        PrintUtil.printPrimitiveBoard(board);
        System.out.println();
        PrintUtil.printMoveValuesTable(list);
    }
}