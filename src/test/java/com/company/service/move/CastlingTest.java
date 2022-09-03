package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.chess_exception.ChessException;
import com.company.model.command.Command;
import com.company.model.command.CommandEnum;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;
import com.company.model.player.Player;
import org.junit.jupiter.api.Test;
import com.company.model.board.BoardLoader;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class CastlingTest {

    Team WHITE = Team.WHITE;
    Team BLACK = Team.BLACK;

    @Test
    void exceptionCastlingToUnderAttackTrue() {
        String stringCommand = CommandEnum.RIGHT_CASTLING.getKey();

        Triple[] test = new Triple[]{
                Triple.of("kbe8, rbh8, rba8, rwg1", BLACK, stringCommand),
                Triple.of("kbe8, rbh8, rba8, nwh6", BLACK, stringCommand),
                Triple.of("kbe8, rbh8, rba8, pwh7", BLACK, stringCommand),
                Triple.of("kbe8, rbh8, rba8, bwd6", BLACK, stringCommand),
        };

        for (Triple t : test) {
            Team team = (Team) t.second;
            Player player = new Player(team);

            String string = (String) t.first;
            Board board = BoardLoader.boardOf(string);
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
        String stringCommand = CommandEnum.LEFT_CASTLING.getKey();

        Triple[] test = new Triple[]{
                Triple.of("kbe8, rbh8, rba8, rwg1", BLACK, stringCommand),
                Triple.of("kbe8, rbh8, rba8, nwh6", BLACK, stringCommand),
                Triple.of("kbe8, rbh8, rba8, pwh7", BLACK, stringCommand),
        };

        for (Triple t : test) {
            Team team = (Team) t.second;
            Player player = new Player(team);

            String string = (String) t.first;
            Board board = BoardLoader.boardOf(string);
            Command command = new Command(stringCommand);
            Danger danger = new Danger(board, team);

            Move castling = new Castling(board);

            castling.execute(command, player, danger);  //нет исключения- все ок

        }
    }

}