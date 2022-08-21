package com.company.controller.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.figure.FigureWithStatistic;
import com.company.model.player.Player;

public class MoveController {

    public MoveController() {
    }

    public void move(Board board, Command command, Player current) {
        String[] array = command.getString().toLowerCase().split("-");

        Cell from = Board.toCell(array[0]);
        Cell to = Board.toCell(array[1]);

        verifyPosition(board, from, to);
        FigureWithStatistic figure = board.get(from);

        if (figure.isNull()) {
            String message = String.format("Ход невозможен: на клетке %s нет фигуры", Board.toPosition(from));
            throw new IllegalArgumentException(message);
        }

        if (figure.getColor() != current.getColor()) {
            String message = String.format("Ход невозможен: фигура на %s принадлежит другому игроку", Board.toPosition(from));
            throw new IllegalArgumentException(message);
        }

        MoveType type = moveTypeOf(board, from, to);

        type.verify(board, from, to);
        type.execute(board, from, to);
    }

    private void verifyPosition(Board board, Cell from, Cell to) {
        if (!board.isCorrect(from) || !board.isCorrect(to)) {
            String message = "Ход находится за границей доски";
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean isCasting(Board board, Cell from, Cell to) {
        FigureWithStatistic figureFrom = board.get(from);
        FigureWithStatistic figureTo = board.get(to);
        return ((figureFrom.isRock() && figureTo.isKing()) || (figureFrom.isKing() && figureTo.isRock())
                && (figureFrom.getColor() == figureTo.getColor())
        );
    }


    private static MoveType moveTypeOf(Board board, Cell from, Cell to) {
        if (isCasting(board, from, to)) {
            return new CastingController();
        }
        return new StepController();

    }
}
