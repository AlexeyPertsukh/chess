package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.unit.Unit;
import com.company.model.player.Player;

public class Move {
    private final Board board;

    public Move(Board board) {
        this.board = board;
    }

    public void execute(Command command, Player current, DangerMatrix dangerMatrix) {

        String[] array = commandToPositions(current, command);

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

    private static boolean isCastling(Board board, Cell from, Cell to) {
        Unit unitFrom = board.get(from);
        Unit unitTo = board.get(to);
        if (unitTo.isNull()) {
            return false;
        }
        return ((unitFrom.isRock() && unitTo.isKing()) || (unitFrom.isKing() && unitTo.isRock())
                && (unitFrom.getColor() == unitTo.getColor())
        );
    }

    private static MoveType moveTypeOf(Board board, Cell from, Cell to) {
        if (isCastling(board, from, to)) {
            return new Castling(board);
        }
        return new Step(board);
    }

    private static String[] castlingToPositions(Player player, Command command) {
        String string = command.getString();
        int row = player.getColor() == FigureColor.WHITE ? 1 : 8;
        String e = "e" + row;
        String h = "h" + row;
        String a = "a" + row;

        switch (string) {
            case (Command.R_CASTLING):
                return new String[]{e, h};

            case (Command.L_CASTLING):
                return new String[]{e, a};

            default:
                String message = String.format("this command is not castling: %s", string);
                throw new IllegalArgumentException(message);
        }
    }

    private static String[] commandToPositions(Player player, Command command) {
        String string = command.getString();
        String[] out;
        if (command.isCastling()) {
            out = castlingToPositions(player, command);
        } else {
            out = command.getString().toLowerCase().split("-");
        }
        return out;
    }


}
