package com.company.controller;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.Figure;
import com.company.model.figure.Step;
import com.company.model.player.Player;

public class MoveController {

    private MoveController() {
    }

    public static void move(Board board, String command, Player current) {
        String[] array = command.replace(" ", "").split("-");
        if (array.length != 2) {
            throw new IllegalArgumentException("Некорректная команда");
        }
        String from = array[0];
        String to = array[1];
        verifyPosition(board, from, to);
        Figure figure = board.get(from);

        if (figure.isNull()) {
            String message = String.format("Ход невозможен: на клетке %s нет фигуры %n", from);
            throw new IllegalArgumentException(message);
        }

        if (figure.getColor() != current.getColor()) {
            throw new IllegalArgumentException("Ход невозможен: фигура другого игрока");
        }

        verifyStep(board, figure, from, to);

        figure = board.remove(from);
        board.insert(figure, to);
    }

    private static void verifyPosition(Board board, String from, String to) {
        if (!board.isCorrect(from) || !board.isCorrect(to)) {
            String message = "Ход невозможен: позиция в ходе выходит за границы доски";
            throw new IllegalArgumentException(message);
        }
    }

    private static void verifyStep(Board board, Figure figure, String from, String to) {
        Figure figureTo = board.get(to);

        if (figure.getColor() == figureTo.getColor()) {
            String message = String.format("Ход невозможен: на клетке %s находится фигура того же цвета", to);
            throw new IllegalArgumentException();
        }

        boolean isAttack = figureTo.isNull();

        Cell cell = board.toCell(to);
        int row = cell.row;
        int column = cell.column;

        ok(board, from, to);

    }

    private static boolean ok(Board board, String from, String to) {
        Cell cellFrom = board.toCell(from);
        Cell cellTo = board.toCell(to);
        int offsetRow = cellTo.row - cellFrom.row;
        int offsetColumn = cellTo.column - cellFrom.column;
        while (offsetRow % 2 == 0 && offsetColumn % 2 == 0) {
            offsetColumn /= 2;
            offsetRow /= 2;
        }

        return true;
    }

//    private static final boolean isVertical(Cell from, Cell to) {
//        return (from.column == to.column || from.row == to.row)
//    }
//
//    private static final boolean isVertical(Cell from, Cell to) {
//        return (from.column == to.column || from.row == to.row)
//    }


}
