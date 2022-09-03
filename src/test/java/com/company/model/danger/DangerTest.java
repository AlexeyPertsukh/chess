package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.piece.figure.Team;
import org.junit.jupiter.api.Test;
import com.company.model.board.BoardFactory;
import test_util.PrintUtil;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class DangerTest {

    @Test
    void isCheckTrue() {

        Team white = Team.WHITE;
        Team black = Team.BLACK;

        Triple[] test = new Triple[]{
                Triple.of("kbe5, pwf4", black),
                Triple.of("kbe5, nwd3", black),
                Triple.of("kbe5, bwh2", black),
                Triple.of("kbe5, qbe2, bwh2", black),
        };

        for (Triple t : test) {
            Board board = BoardFactory.createdOf((String) t.first);
            Team aggressorColor = (Team) t.second;
            Danger danger = new Danger(board, aggressorColor);
            assertTrue(danger.isCheck());
        }

    }

//    @Test
    void test1() {
        Board board = BoardFactory.createdOf("pbb3, qwe3, nwd5, qba7, kbe7, pwf6, kwf2");
        Danger danger = new Danger(board, Team.WHITE);
        PrintUtil.printPrimitiveBoard(board);
        PrintUtil.printBoard(board);
        PrintUtil.print(danger);

    }

    @Test
    void isCheckmateTrue() {

        Team white = Team.WHITE;
        Team black = Team.BLACK;

        Triple[] test = new Triple[]{
//                Triple.of("kbh8, qwh7, rwa7", black),
                Triple.of("pwd3, pwe3, pwf3, rwa5, kbe5, pbd6, pbe6, pbf6", black),

        };

        for (Triple t : test) {
            Board board = BoardFactory.createdOf((String) t.first);
            Danger danger = new Danger(board, black);

            PrintUtil.printBoard(board);
            PrintUtil.print(danger);

            assertTrue(danger.isCheckmate());

//            Team aggressorColor = (Team) t.second;
//            Danger danger = new Danger(board, aggressorColor);
//            assertTrue(danger.isCheck());
        }

    }

}