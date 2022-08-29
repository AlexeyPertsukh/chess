package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.danger.Danger;
import com.company.model.player.Player;
import com.company.model.piece.Piece;
import com.company.model.board.Way;

public abstract class Move {
    protected final Board board;

    public Move(Board board) {
        this.board = board;
    }

    public void execute(Command command, Player current, Danger danger) {
        Cell[] cells = commandToCells(current, command);

        Cell from = cells[0];
        Cell to = cells[1];

        Way way = new Way(from, to);
        verifyAvailablePosition(board, way);

        Piece piece = board.get(from);
        if (piece.isNull()) {
            String message = messageNoUnit(from);
            throw new IllegalArgumentException(message);
        }

        if (piece.getTeam() != current.getTeam()) {
            String message = messageAlienUnit(from);
            throw new IllegalArgumentException(message);
        }

        specialVerify(way, danger);
        action(way);

    }

    protected abstract void action(Way way);

    private void verifyAvailablePosition(Board board, Way way) {
        if (!board.isCorrect(way.from) || !board.isCorrect(way.to)) {
            String message = "The move is off the board";
            throw new IllegalArgumentException(message);
        }
    }

    protected abstract Cell[] commandToCells(Player player, Command command);

    protected abstract void specialVerify(Way way, Danger danger);

    protected abstract String messageNoUnit(Cell cell);
    protected abstract String messageAlienUnit(Cell cell);

}
