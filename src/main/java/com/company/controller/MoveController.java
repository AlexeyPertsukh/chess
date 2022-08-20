package com.company.controller;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;
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

        verifyStep(board, from, to);

        figure = board.remove(from);
        board.insert(figure, to);
    }

    private static void verifyPosition(Board board, String from, String to) {
        if (!board.isCorrect(from) || !board.isCorrect(to)) {
            String message = "Ход невозможен: позиция в ходе выходит за границы доски";
            throw new IllegalArgumentException(message);
        }
    }

    private static void verifyStep(Board board, String from, String to) {
        Figure figureFrom = board.get(from);
        Figure figureTo = board.get(to);

        if (figureFrom.getColor() == figureTo.getColor()) {
            String message = String.format("Ход невозможен: на клетке %s находится фигура того же цвета", to);
            throw new IllegalArgumentException(message);
        }

//        boolean isAttack = figureTo.isNull();

//        Cell cell = board.toCell(to);
//        int row = cell.row;
//        int column = cell.column;

        if (!checkCorrectStep(board, from, to)) {
            String message = "Ход невозможен: фигура так не ходит или между клетками есть препятствие";
            throw new IllegalArgumentException(message);
        }

    }

    private static boolean checkCorrectStep(Board board, String from, String to) {
        Figure figure = board.get(from);

        Cell cellFrom = board.toCell(from);
        Cell cellTo = board.toCell(to);

        if (isPawn(figure)) {
            return isPawnStep(board, cellFrom, cellTo);
        }

        if (isRock(figure)) {
            return isLineStep(board, cellFrom, cellTo);
        }

        if (isKnight(figure)) {
            return isCornerStep(board, cellFrom, cellTo);
        }

        if (isBishop(figure)) {
            return isDiagonalStep(board, cellFrom, cellTo);
        }

        if (isQueen(figure)) {
            return isLineStep(board, cellFrom, cellTo) || isDiagonalStep(board, cellFrom, cellTo);
        }

        if (isKing(figure)) {
            return isKingStep(board, cellFrom, cellTo);
        }

        return false;
    }

    protected static boolean isPawnStep(Board board, Cell from, Cell to) {
        FigureColor figureColor = board.get(from).getColor();

        if (figureColor == FigureColor.WHITE) {
            return (from.row - to.row == 1 && from.column == to.column) ||
                    (from.row == Board.SIZE - 2 && to.row == from.row - 2);
        }

        return (from.row - to.row == -1 && from.column == to.column) ||
                (from.row == 1 && to.row == from.row + 2);
    }

    protected static boolean isLineStep(Board board, Cell from, Cell to) {
        if (from.column != to.column && from.row != to.row) {
            return false;
        }

        return true;
    }

    protected static boolean isDiagonalStep(Board board, Cell from, Cell to) {
        int a = Math.abs(to.column - from.column);
        int b = Math.abs(to.row - from.row);

        return a == b;
    }

    protected static boolean isCornerStep(Board board, Cell from, Cell to) {
        int a = Math.abs(to.column - from.column);
        int b = Math.abs(to.row - from.row);

        return (a == 2 && b == 1) || (a == 1 && b == 2);
    }

    protected static boolean isKingStep(Board board, Cell from, Cell to) {
        int a = Math.abs(to.column - from.column);
        int b = Math.abs(to.row - from.row);

        return a < 2 && b < 2;
    }


    private static boolean isPawn(Figure figure) {
        return figure == Figure.PAWN_WHITE || figure == Figure.PAWN_BLACK;
    }

    private static boolean isRock(Figure figure) {
        return figure == Figure.ROCK_WHITE || figure == Figure.ROCK_BLACK;
    }


    private static boolean isKnight(Figure figure) {
        return figure == Figure.KNIGHT_WHITE || figure == Figure.KNIGHT_BLACK;
    }

    private static boolean isBishop(Figure figure) {
        return figure == Figure.BISHOP_WHITE || figure == Figure.BISHOP_BLACK;
    }

    private static boolean isQueen(Figure figure) {
        return figure == Figure.QUEEN_WHITE || figure == Figure.QUEEN_BLACK;
    }

    private static boolean isKing(Figure figure) {
        return figure == Figure.KING_WHITE || figure == Figure.KING_BLACK;
    }

}
