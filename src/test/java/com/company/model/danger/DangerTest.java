package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.piece.figure.Rank;
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
                Triple.of("ke5, Pf4", black),
                Triple.of("ke5, Nd3", black),
                Triple.of("ke5, Bh2", black),
                Triple.of("ke5, qe2, Bh2", black),
        };

        for (Triple t : test) {
            Board board = BoardFactory.createdOf((String) t.first);
            Team team = (Team) t.second;
            Danger danger = new Danger(board, team);

            assertTrue(isCheck(board, danger));
        }

    }

    private static boolean isCheck(Board board, Danger danger) {
        Cell cellKing = board.find(Rank.KING, danger.getTeam());
        return danger.isUnderAttack(cellKing);
    }

//    @Test
    void test1() {
        Board board = BoardFactory.createdOf("pb3, Qe3, Nd5, qa7, ke7, Pf6, Kf2");
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
                Triple.of("Pd3, Pe3, Pf3, Ra5, ke5, pd6, pe6, pf6", black),

        };

        for (Triple t : test) {
            Board board = BoardFactory.createdOf((String) t.first);
            Danger danger = new Danger(board, black);

            PrintUtil.printBoard(board);
            PrintUtil.print(danger);

            assertTrue(isCheck(board, danger));

//            Team aggressorColor = (Team) t.second;
//            Danger danger = new Danger(board, aggressorColor);
//            assertTrue(danger.isCheck());
        }

    }

}