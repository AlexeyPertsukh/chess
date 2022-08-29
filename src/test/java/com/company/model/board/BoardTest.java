package com.company.model.board;

import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.Rank;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BoardTest {

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void isCorrect() {
    }

    @Test
    void transfer() {
    }

    private static final String FOR_FIND = "qwd5, pwc5, rbb7, kwg5, pbb5, bbd7, qbg8";

    @Test
    void findEq() {

        final Team white = Team.WHITE;
        final Team black = Team.BLACK;


        Board board = BoardUtil.boardOf(FOR_FIND);

        Triple[] test = new Triple[]{
                Triple.of(Rank.QUEEN, white, "d5"),
                Triple.of(Rank.KING, white, "g5"),
                Triple.of(Rank.QUEEN, black, "g8"),
                Triple.of(Rank.PAWN, black, "b5"),
        };

        for (Triple t : test) {
            Rank rank = (Rank) t.first;
            Team color = (Team) t.second;
            Cell cell = board.find(rank, color);

            String expected = (String) t.third;
            String actual = Board.toPosition(cell);

            assertEquals(expected, actual);
        }
    }

    @Test
    void findNotEq() {

        final Team white = Team.WHITE;
        final Team black = Team.BLACK;


        Board board = BoardUtil.boardOf(FOR_FIND);

        Triple[] test = new Triple[]{
                Triple.of(Rank.QUEEN, white, "a5"),
                Triple.of(Rank.KING, white, "b5"),
                Triple.of(Rank.QUEEN, black, "c8"),
                Triple.of(Rank.PAWN, black, "f5"),
        };

        for (Triple t : test) {
            Rank rank = (Rank) t.first;
            Team color = (Team) t.second;
            Cell cell = board.find(rank, color);

            String expected = (String) t.third;
            String actual = Board.toPosition(cell);

            assertNotEquals(expected, actual);
        }
    }


    private static final Triple[] FOR_TO_CELL_POSITION = new Triple[]{
            Triple.of("a1", new Cell(0, 7)),
            Triple.of("b1", new Cell(1, 7)),
            Triple.of("c1", new Cell(2, 7)),
            Triple.of("d1", new Cell(3, 7)),
            Triple.of("e1", new Cell(4, 7)),
            Triple.of("f1", new Cell(5, 7)),
            Triple.of("g1", new Cell(6, 7)),
            Triple.of("h1", new Cell(7, 7)),

            Triple.of("a8", new Cell(0, 0)),
            Triple.of("b8", new Cell(1, 0)),
            Triple.of("c8", new Cell(2, 0)),
            Triple.of("d8", new Cell(3, 0)),
            Triple.of("e8", new Cell(4, 0)),
            Triple.of("f8", new Cell(5, 0)),
            Triple.of("g8", new Cell(6, 0)),
            Triple.of("h8", new Cell(7, 0)),
    };

    @Test
    void toCell() {

        for (Triple t : FOR_TO_CELL_POSITION) {
            String position = (String) t.first;
            Cell actual = Board.toCell(position);
            Cell expected = (Cell) t.second;

            assertEquals(expected, actual);
        }
    }

    @Test
    void toPosition() {

        for (Triple t : FOR_TO_CELL_POSITION) {
            Cell agr = (Cell) t.second;
            String actual = Board.toPosition(agr);
            String expected = (String) t.first;

            assertEquals(expected, actual);
        }
    }

}