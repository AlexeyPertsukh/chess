package com.company.model.artificial_intelligence;

import com.company.model.board.Board;
import com.company.model.danger.Danger;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.player.Bot;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.PrintUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AiTest {

    @Test
    void allPossibleMoves() {
        FigureColor myColor = FigureColor.WHITE;
        Board board = BoardUtil.getBoard("qwd5, pwc5, kwg5, pbb5, bbd7, qbg8");

        Ai ai = new Ai(board);
        Danger danger = new Danger(board);
        DangerMatrix dangerMatrix = danger.toMatrix(FigureColor.BLACK);
        List<Ai.PossibleMove> list = ai.allPossibleMoves(myColor, dangerMatrix);

        PrintUtil.print(list);
        System.out.println();
        PrintUtil.printPrimitiveBoard(board);
        System.out.println();
        PrintUtil.printMoveValuesTable(list);
    }
}