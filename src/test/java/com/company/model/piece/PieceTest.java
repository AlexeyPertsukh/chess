package com.company.model.piece;

import com.company.model.board.Board;
import com.company.model.board.BoardLoader;
import com.company.model.command.Command;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;
import com.company.model.player.Player;
import com.company.service.move.Move;
import com.company.service.move.Step;
import org.junit.jupiter.api.Test;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    final static Team WHITE = Team.WHITE;
    final static Team BLACK = Team.BLACK;

    private static final Triple[] ARRAY_TEST_UNDO = new Triple[]{
            Triple.of(Pawn.of(WHITE), Pawn.of(WHITE)),
            Triple.of(Bishop.of(WHITE), Bishop.of(WHITE)),
            Triple.of(Bishop.of(BLACK), Bishop.of(BLACK)),
    };

    @Test
    void testEqualsTrue() {

        for (Triple t : ARRAY_TEST_UNDO) {
            Piece piece1 = (Piece) t.first;
            Piece piece2 = (Piece) t.second;

            boolean actual = piece1.equals(piece2);
            assertTrue(actual);
        }
    }

    @Test
    void testEqualsFalse() {

        for (Triple t : ARRAY_TEST_UNDO) {
            Piece piece1 = (Piece) t.first;
            Piece piece2 = (Piece) t.second;
            piece2.incMoveCount();

            boolean actual = piece1.equals(piece2);
            assertFalse(actual);
        }

        final Triple[] test = new Triple[]{
                Triple.of(Pawn.of(WHITE), Pawn.of(BLACK)),
                Triple.of(Queen.of(WHITE), Bishop.of(WHITE)),
                Triple.of(Bishop.of(WHITE), King.of(WHITE)),
                Triple.of(Bishop.of(WHITE), King.of(BLACK)),
        };

        for (Triple t : test) {
            Piece piece1 = (Piece) t.first;
            Piece piece2 = (Piece) t.second;

            boolean actual = piece1.equals(piece2);
            assertFalse(actual);
        }

    }

}