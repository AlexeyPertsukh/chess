package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.danger.DangerMatrix;
import com.company.model.player.Player;
import com.company.model.unit.Unit;
import com.company.model.way.Way;

public abstract class Move {
    protected final Board board;

    public Move(Board board) {
        this.board = board;
    }

    public void execute(Command command, Player current, DangerMatrix dangerMatrix) {
        Cell[] cells = commandToCells(current, command);

        Cell from = cells[0];
        Cell to = cells[1];

        Way way = new Way(from, to);
        verifyAvailablePosition(board, way);

        Unit unit = board.get(from);
        if (unit.isNull()) {
            String message = messageNoUnit(from);
            throw new IllegalArgumentException(message);
        }

        if (unit.getColor() != current.getColor()) {
            String message = messageAlienUnit(from);
            throw new IllegalArgumentException(message);
        }

        specialVerify(way, dangerMatrix);
        action(way);

    }

    protected abstract void action(Way way);

    private void verifyAvailablePosition(Board board, Way way) {
        if (!board.isCorrect(way.from) || !board.isCorrect(way.to)) {
            String message = "Ход находится за границей доски";
            throw new IllegalArgumentException(message);
        }
    }

    protected abstract Cell[] commandToCells(Player player, Command command);

    protected abstract void specialVerify(Way way, DangerMatrix dangerMatrix);

    protected abstract String messageNoUnit(Cell cell);
    protected abstract String messageAlienUnit(Cell cell);

}
