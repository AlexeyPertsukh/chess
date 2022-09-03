package com.company.model.piece;

import com.company.model.piece.figure.Team;
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
            Triple.of(PieceNull.getInstance(), PieceNull.getInstance()),
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
            Piece piece2 = null;
            try {
                piece2 = (Piece) ((Piece) t.second).clone();
            } catch (CloneNotSupportedException e) {
                throw new IllegalArgumentException("exception testEqualsFalse");
            }

            if (piece2.isNull()) {
                continue;
            }
            piece2.incMoveCount();

            boolean actual = piece1.equals(piece2);
            assertFalse(actual);
        }

        final Triple[] test = new Triple[]{
                Triple.of(Pawn.of(WHITE), Pawn.of(BLACK)),
                Triple.of(Queen.of(WHITE), Bishop.of(WHITE)),
                Triple.of(Bishop.of(WHITE), King.of(WHITE)),
                Triple.of(Bishop.of(WHITE), King.of(BLACK)),
                Triple.of(Bishop.of(WHITE), PieceNull.getInstance()),
        };

        for (Triple t : test) {
            Piece piece1 = (Piece) t.first;
            Piece piece2 = (Piece) t.second;

            boolean actual = piece1.equals(piece2);
            assertFalse(actual);
        }

    }

}