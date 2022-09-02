package com.company.model.artificial_intelligence.board;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.chess_exception.ChessException;
import com.company.model.command.Command;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.Rank;
import com.company.model.player.Player;
import com.company.service.move.Move;
import com.company.service.move.Step;
import org.junit.jupiter.api.Test;
import com.company.model.board.BoardLoader;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    final static Team WHITE = Team.WHITE;
    final static Team BLACK = Team.BLACK;

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

        Board board = BoardLoader.boardOf(FOR_FIND);

        Triple[] test = new Triple[]{
                Triple.of(Rank.QUEEN, WHITE, "d5"),
                Triple.of(Rank.KING, WHITE, "g5"),
                Triple.of(Rank.QUEEN, BLACK, "g8"),
                Triple.of(Rank.PAWN, BLACK, "b5"),
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

        Board board = BoardLoader.boardOf(FOR_FIND);

        Triple[] test = new Triple[]{
                Triple.of(Rank.QUEEN, WHITE, "a5"),
                Triple.of(Rank.KING, WHITE, "b5"),
                Triple.of(Rank.QUEEN, BLACK, "c8"),
                Triple.of(Rank.PAWN, BLACK, "f5"),
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

    @Test
    void exceptionOnMoveIfCheckToKingTrue() {

        Triple[] test = new Triple[]{
                Triple.of("kbe6, nbe5, qwe1", "e5-g6", BLACK),
                Triple.of("kbe6, pbf5, bwh3", "f5-f4", BLACK),
                Triple.of("kbe6, rbg5, rwe3", "e6-e7", BLACK),
        };


        for (Triple t : test) {
            Board board = BoardLoader.boardOf((String) t.first);
            Command command = new Command((String) t.second);
            Move step = new Step(board);
            Team team = (Team) t.third;
            Player player = new Player(team);
            Danger danger = new Danger(board, team);

            RuntimeException e = assertThrows(ChessException.class, () -> {
                step.execute(command, player, danger);
            });

            boolean actual = e.getMessage().contentEquals("Move failed: check to the king");
            assertTrue(actual);
        }
    }


    @Test
    void exceptionOnMoveIfCheckToKingFalse() {

        Triple[] test = new Triple[]{
                Triple.of("kbe6, nbe5, qwe1", "e6-d6", BLACK),
                Triple.of("kbe6, pbf5, bwh3", "e6-e5", BLACK),
                Triple.of("kbe6, rbg5, rwe3", "g5-e5", BLACK),
        };


        for (Triple t : test) {
            Board board = BoardLoader.boardOf((String) t.first);
            Command command = new Command((String) t.second);
            Move step = new Step(board);
            Team team = (Team) t.third;
            Player player = new Player(team);
            Danger danger = new Danger(board, team);

            step.execute(command, player, danger);

        }
    }


}