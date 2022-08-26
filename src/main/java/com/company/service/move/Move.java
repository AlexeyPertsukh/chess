package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.danger.DangerMatrix;
import com.company.model.player.Player;
import com.company.model.unit.Unit;

public abstract class Move {
    protected final Board board;

    public Move(Board board) {
        this.board = board;
    }

    public void execute(Command command, Player current, DangerMatrix dangerMatrix) {
        Cell[] cells = commandToCells(current, command);

        Cell from = cells[0];
        Cell to = cells[1];

        verifyAvailablePosition(board, from, to);

        Unit unit = board.get(from);
        if (unit.isNull()) {
            String message = messageNoUnit(from);
            throw new IllegalArgumentException(message);
        }

        if (unit.getColor() != current.getColor()) {
            String message = messageAlienUnit(from);
            throw new IllegalArgumentException(message);
        }

        specialVerify(from, to, dangerMatrix);
        action(from, to);

    }

    protected abstract void action(Cell from, Cell to);

    private void verifyAvailablePosition(Board board, Cell from, Cell to) {
        if (!board.isCorrect(from) || !board.isCorrect(to)) {
            String message = "Ход находится за границей доски";
            throw new IllegalArgumentException(message);
        }
    }

    protected abstract Cell[] commandToCells(Player player, Command command);

    protected abstract void specialVerify(Cell from, Cell to, DangerMatrix dangerMatrix);

    protected abstract String messageNoUnit(Cell cell);
    protected abstract String messageAlienUnit(Cell cell);

}
