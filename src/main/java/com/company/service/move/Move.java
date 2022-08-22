package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.danger.DangerMatrix;
import com.company.model.unit.Unit;
import com.company.model.player.Player;

public class Move {
    private final Board board;

    public Move(Board board) {
        this.board = board;
    }

    public void execute(Command command, Player current, DangerMatrix dangerMatrix) {
        String[] array = command.getString().toLowerCase().split("-");

        Cell from = Board.toCell(array[0]);
        Cell to = Board.toCell(array[1]);

        verifyPosition(board, from, to);
        Unit unit = board.get(from);

        if (unit.isNull()) {
            String message = String.format("Ход невозможен: на клетке %s нет фигуры", Board.toPosition(from));
            throw new IllegalArgumentException(message);
        }

        if (unit.getColor() != current.getColor()) {
            String message = String.format("Ход невозможен: фигура на %s принадлежит другому игроку", Board.toPosition(from));
            throw new IllegalArgumentException(message);
        }

        MoveType type = moveTypeOf(board, from, to);

        type.verify(from, to, dangerMatrix);
        type.execute(from, to);
    }

    private void verifyPosition(Board board, Cell from, Cell to) {
        if (!board.isCorrect(from) || !board.isCorrect(to)) {
            String message = "Ход находится за границей доски";
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean isCasting(Board board, Cell from, Cell to) {
        Unit unitFrom = board.get(from);
        Unit unitTo = board.get(to);
        if(unitTo.isNull()) {
            return false;
        }
        return ((unitFrom.isRock() && unitTo.isKing()) || (unitFrom.isKing() && unitTo.isRock())
                && (unitFrom.getColor() == unitTo.getColor())
        );
    }

    private static MoveType moveTypeOf(Board board, Cell from, Cell to) {
        if (isCasting(board, from, to)) {
            return new Casting(board);
        }
        return new Step(board);

    }
}
