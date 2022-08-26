package com.company.model.player;

import com.company.model.board.Board;
import com.company.model.figure.FigureColor;
import com.company.model.unit.Bishop;
import com.company.model.unit.Pawn;
import com.company.model.unit.Queen;
import com.company.model.unit.Rock;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.PrintUtil;

import java.util.List;

class BotTest {

    @Test
    void allPossibleMoves() {
        Board board = BoardUtil.getBoard("qwd5, kwg5, pbb5, bbd7, qbg8");

        Bot bot = new Bot(FigureColor.WHITE, board);
        List<Bot.PossibleMove> list = bot.allPossibleMoves(bot.getColor(), null);

        PrintUtil.print(list);
        PrintUtil.printMoveValuesTable(list);
    }
}