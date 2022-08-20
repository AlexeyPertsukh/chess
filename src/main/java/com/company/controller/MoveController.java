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

    private static void verifyStep(Board board, String posFrom, String posTo) {
        Figure figureFrom = board.get(posFrom);
        Figure figureTo = board.get(posTo);

        Cell from = Board.toCell(posFrom);
        Cell to = Board.toCell(posTo);

        if (!isCorrectDirection(board,from, to)) {
            String message = "Ход невозможен: фигура так не ходит";
            throw new IllegalArgumentException(message);
        }

        if(!isKnight(figureFrom) && !isWayWithoutObstacles(board, from, to)) {
            String message = "Ход невозможен: на пути фигуры есть препятствие";
            throw new IllegalArgumentException(message);
        }

        if (figureFrom.getColor() == figureTo.getColor()) {
            String message = String.format("Ход невозможен: на клетке %s находится фигура того же цвета", posTo);
            throw new IllegalArgumentException(message);
        }

    }

    private static boolean isCorrectDirection(Board board, Cell from, Cell to) {
        Figure figure = board.get(from);

        if (isPawn(figure)) {
            return isPawnDirection(board, from, to);
        }

        if (isRock(figure)) {
            int i = 0;
            return isLineDirection(board, from, to);
        }

        if (isKnight(figure)) {
            return isCornerDirection(board, from, to);
        }

        if (isBishop(figure)) {
            return isDiagonalDirection(board, from, to);
        }

        if (isQueen(figure)) {
            return isLineDirection(board, from, to) || isDiagonalDirection(board, from, to);
        }

        if (isKing(figure)) {
            return isKingDirection(board, from, to);
        }

        return false;
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

    protected static boolean isPawnDirection(Board board, Cell from, Cell to) {
        FigureColor figureColor = board.get(from).getColor();

        if (figureColor == FigureColor.WHITE) {
            return (from.row - to.row == 1 && from.column == to.column) ||
                    (from.row == Board.SIZE - 2 && to.row == from.row - 2);
        }

        return (from.row - to.row == -1 && from.column == to.column) ||
                (from.row == 1 && to.row == from.row + 2);
    }

    protected static boolean isPawnAttack(Board board, Cell from, Cell to) {
        FigureColor figureColor = board.get(from).getColor();

        if(Math.abs(from.column - to.column) != 1) {
            return false;
        }

        if (figureColor == FigureColor.WHITE) {
            return from.row - to.row == 1;
        }

        return from.row - to.row == -1;
    }

    protected static boolean isLineDirection(Board board, Cell from, Cell to) {
        return  from.column == to.column || from.row == to.row;

    }

    protected static boolean isDiagonalDirection(Board board, Cell from, Cell to) {
        int a = Math.abs(to.column - from.column);
        int b = Math.abs(to.row - from.row);

        return a == b;
    }

    protected static boolean isCornerDirection(Board board, Cell from, Cell to) {
        int a = Math.abs(to.column - from.column);
        int b = Math.abs(to.row - from.row);

        return (a == 2 && b == 1) || (a == 1 && b == 2);
    }

    protected static boolean isKingDirection(Board board, Cell from, Cell to) {
        int a = Math.abs(to.column - from.column);
        int b = Math.abs(to.row - from.row);

        return a < 2 && b < 2;
    }

    protected static boolean isWayWithoutObstacles(Board board, Cell from, Cell to) {
        int offsetRow = sign(to.row - from.row);
        int offsetColumn = sign(to.column - from.column);

        Cell cell = from;
        while(true) {
            cell = new Cell(cell.column + offsetColumn, cell.row + offsetRow);
            if(cell.equals(to)) {
                break;
            }
            if(!board.get(cell).isNull()) {
                return false;
            }
        }

        return true;
    }

    private static int sign(int num) {
        if(num == 0) {
            return 0;
        }
        return num > 0 ? 1 : -1;
    }



}
