package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.chess_exception.ChessException;
import com.company.model.command.Command;
import com.company.model.command.CommandEnum;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;
import com.company.model.player.Player;
import org.junit.jupiter.api.Test;
import test_util.BoardUtil;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class CastlingTest {

    @Test
    void exceptionCastlingToUnderAttackTrue() {
        Team white = Team.WHITE;
        Team black = Team.BLACK;

        String stringCommand = CommandEnum.RIGHT_CASTLING.getKey();

        Triple[] test = new Triple[]{
                Triple.of("kbe8, rbh8, rba8, rwg1", black, stringCommand),
                Triple.of("kbe8, rbh8, rba8, nwh6", black, stringCommand),
                Triple.of("kbe8, rbh8, rba8, pwh7", black, stringCommand),
                Triple.of("kbe8, rbh8, rba8, bwd6", black, stringCommand),
        };

        for (Triple t : test) {
            Team team = (Team) t.second;
            Player player = new Player(team);

            String string = (String) t.first;
            Board board = BoardUtil.boardOf(string);
            Command command = new Command(stringCommand);
            Danger danger = new Danger(board, team);

            Move castling = new Castling(board);

            assertThrows(ChessException.class, () -> {
                castling.execute(command, player, danger);
            });

        }
    }

    @Test
    void exceptionCastlingToUnderAttackFalse() {
        Team white = Team.WHITE;
        Team black = Team.BLACK;
        String stringCommand = CommandEnum.LEFT_CASTLING.getKey();

        Triple[] test = new Triple[]{
                Triple.of("kbe8, rbh8, rba8, rwg1", black, stringCommand),
                Triple.of("kbe8, rbh8, rba8, nwh6", black, stringCommand),
                Triple.of("kbe8, rbh8, rba8, pwh7", black, stringCommand),
        };

        for (Triple t : test) {
            Team team = (Team) t.second;
            Player player = new Player(team);

            String string = (String) t.first;
            Board board = BoardUtil.boardOf(string);
            Command command = new Command(stringCommand);
            Danger danger = new Danger(board, team);

            Move castling = new Castling(board);

            castling.execute(command, player, danger);  //нет исключения- все ок

        }
    }

}