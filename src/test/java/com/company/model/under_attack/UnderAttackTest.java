package com.company.model.under_attack;

import com.company.model.board.Board;
import com.company.model.board.BoardFactory;
import com.company.model.board.Cell;
import com.company.model.piece.figure.Team;
import com.company.model.under_attack.UnderAttack;
import org.junit.jupiter.api.Test;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class UnderAttackTest {

    Team WHITE = Team.WHITE;
    Team BLACK = Team.BLACK;

    @Test
    void isUnderAttackTrue() {
        Triple[] test = new Triple[]{
                Triple.of("Qe3, Ne4, ng5, kh6", "g5", BLACK),
                Triple.of("Qe3, Ne4, ng5, kh6", "f6", BLACK),
                Triple.of("Qe3, Ne4, ng5, kh6", "g1", BLACK),
                Triple.of("Qe3, Ne4, ng5, kh6", "d6", BLACK),
                Triple.of("Qe3, Ne4, ng5, kh6", "a7", BLACK),
        };

        for (Triple t : test) {
            Board board = BoardFactory.createdOf((String) t.first);
            Cell cell = Board.toCell((String) t.second);
            Team team = (Team) t.third;

            boolean actual = UnderAttack.isUnderAttack(board, team, cell);
            assertTrue(actual);
        }
    }

    @Test
    void isUnderAttackFalse() {
        Triple[] test = new Triple[]{
                Triple.of("Qe3, Ne4, ng5, kh6", "h6", BLACK),
                Triple.of("Qe3, Ne4, ng5, kh6", "g4", BLACK),
                Triple.of("Qe3, Ne4, ng5, kh6", "f1", BLACK),
                Triple.of("Qe3, Ne4, ng5, kh6", "c4", BLACK),
        };

        for (Triple t : test) {
            Board board = BoardFactory.createdOf((String) t.first);
            Cell cell = Board.toCell((String) t.second);
            Team team = (Team) t.third;

            boolean actual = UnderAttack.isUnderAttack(board, team, cell);
            assertFalse(actual);
        }
    }
}