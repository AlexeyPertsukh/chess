package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.chess_exception.ChessException;
import com.company.model.command.Command;
import com.company.model.command.CommandEnum;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;
import com.company.model.player.Player;
import org.junit.jupiter.api.Test;
import com.company.model.board.BoardFactory;
import test_util.triple.Triple;

import static org.junit.jupiter.api.Assertions.*;

class CastlingTest {

    Team WHITE = Team.WHITE;
    Team BLACK = Team.BLACK;

    @Test
    void exceptionCastlingToUnderAttackTrue() {
        String stringCommand = CommandEnum.RIGHT_CASTLING.getKey();

        Triple[] test = new Triple[]{
                Triple.of("ke8, rh8, ra8, Rg1", BLACK, stringCommand),
                Triple.of("ke8, rh8, ra8, Nh6", BLACK, stringCommand),
                Triple.of("ke8, rh8, ra8, Ph7", BLACK, stringCommand),
                Triple.of("ke8, rh8, ra8, Bd6", BLACK, stringCommand),
        };

        for (Triple t : test) {
            Team team = (Team) t.second;
            Player player = new Player(team);

            String string = (String) t.first;
            Board board = BoardFactory.createdOf(string);
            Command command = new Command(stringCommand);
            Danger danger = new Danger(board, team);

            Turn castling = new Castling(board);

            assertThrows(ChessException.class, () -> {
                castling.execute(command, player, danger);
            });

        }
    }

    @Test
    void exceptionCastlingToUnderAttackFalse() {
        String stringCommand = CommandEnum.LEFT_CASTLING.getKey();

        Triple[] test = new Triple[]{
                Triple.of("ke8, rh8, ra8, Rg1", BLACK, stringCommand),
                Triple.of("ke8, rh8, ra8, Nh6", BLACK, stringCommand),
                Triple.of("ke8, rh8, ra8, Ph7", BLACK, stringCommand),
        };

        for (Triple t : test) {
            Team team = (Team) t.second;
            Player player = new Player(team);

            String string = (String) t.first;
            Board board = BoardFactory.createdOf(string);
            Command command = new Command(stringCommand);
            Danger danger = new Danger(board, team);

            Turn castling = new Castling(board);

            castling.execute(command, player, danger);  //нет исключения- все ок

        }
    }

}