package com.company.controller;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureWithStatistic;
import com.company.model.player.Player;

public class MoveController {

    public MoveController() {

    }

    public void move(Board board, String command, Player current) {
        String[] array = command.replace(" ", "").split("-");
        if (array.length != 2) {
            throw new IllegalArgumentException("Некорректная команда");
        }
        String from = array[0];
        String to = array[1];
        verifyPosition(board, from, to);
        FigureWithStatistic figure = board.get(from);

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
        figure.incMoveCount();
    }

    private void verifyPosition(Board board, String from, String to) {
        if (!board.isCorrect(from) || !board.isCorrect(to)) {
            String message = "Ход невозможен: позиция в ходе выходит за границы доски";
            throw new IllegalArgumentException(message);
        }
    }

    private void verifyStep(Board board, String posFrom, String posTo) {
        FigureWithStatistic figureFrom = board.get(posFrom);
        FigureWithStatistic figureTo = board.get(posTo);

        Cell from = Board.toCell(posFrom);
        Cell to = Board.toCell(posTo);

        if (!isCorrectDirection(board, from, to)) {
            String message = "Ход невозможен: фигура так не ходит";
            throw new IllegalArgumentException(message);
        }

        if (!figureFrom.isKnight() && !isWayWithoutObstacles(board, from, to)) {
            String message = "Ход невозможен: на пути фигуры есть препятствие";
            throw new IllegalArgumentException(message);
        }

        if (figureFrom.getColor() == figureTo.getColor()) {
            String message = String.format("Ход невозможен: в клетке %s находится фигура того же цвета", posTo);
            throw new IllegalArgumentException(message);
        }

    }

    private boolean isCorrectDirection(Board board, Cell from, Cell to) {
        FigureWithStatistic figure = board.get(from);
        boolean attack = !board.get(to).isNull();
        Direction direction = directionOf(figure, attack);
        return direction.isCorrect(from, to);
    }

    protected static boolean isWayWithoutObstacles(Board board, Cell from, Cell to) {
        int offsetRow = sign(to.row - from.row);
        int offsetColumn = sign(to.column - from.column);

        Cell cell = from;
        while (true) {
            cell = new Cell(cell.column + offsetColumn, cell.row + offsetRow);
            if (cell.equals(to)) {
                break;
            }
            if (!board.get(cell).isNull()) {
                return false;
            }
        }

        return true;
    }


    private static int sign(int num) {
        if (num == 0) {
            return 0;
        }
        return num > 0 ? 1 : -1;
    }

    private static abstract class Direction {
        abstract boolean isCorrect(Cell from, Cell to);
    }

    private static class WhitePawnDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            return (from.row - to.row == 1 && from.column == to.column) ||
                    (from.row == Board.SIZE - 2 && to.row == from.row - 2);
        }
    }

    private static class BlackPawnDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            return (from.row - to.row == -1 && from.column == to.column) ||
                    (from.row == 1 && to.row == from.row + 2);
        }
    }

    private static class WhitePawnAttackDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            return from.row - to.row == 1 && Math.abs(from.column - to.column) == 1;
        }
    }

    private static class BlackPawnAttackDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            return from.row - to.row == -1 && Math.abs(from.column - to.column) == 1;
        }
    }

    private static class RockDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            return from.column == to.column || from.row == to.row;
        }
    }

    private static class KnightDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            int a = Math.abs(to.column - from.column);
            int b = Math.abs(to.row - from.row);

            return (a == 2 && b == 1) || (a == 1 && b == 2);
        }
    }

    private static class BishopDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            int a = Math.abs(to.column - from.column);
            int b = Math.abs(to.row - from.row);

            return a == b;
        }
    }

    private static class QueenDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            BishopDirection bishopDirection = new BishopDirection();
            RockDirection rockDirection = new RockDirection();
            return bishopDirection.isCorrect(from, to) || rockDirection.isCorrect(from, to);
        }
    }

    private static class KingDirection extends Direction {
        @Override
        public boolean isCorrect(Cell from, Cell to) {
            int a = Math.abs(to.column - from.column);
            int b = Math.abs(to.row - from.row);

            return a < 2 && b < 2;
        }
    }

    private static Direction directionOf(FigureWithStatistic figure, boolean attack) {
        if (figure.isPawn()) {
            if(attack) {
                return figure.getColor() == FigureColor.WHITE ? new WhitePawnAttackDirection() : new BlackPawnAttackDirection();
            }
            return figure.getColor() == FigureColor.WHITE ? new WhitePawnDirection() : new BlackPawnDirection();
        }

        if (figure.isRock()) {
            return new RockDirection();
        }

        if (figure.isKnight()) {
            return new KnightDirection();
        }

        if (figure.isBishop()) {
            return new BishopDirection();
        }

        if (figure.isQueen()) {
            return new QueenDirection();
        }

        if (figure.isKing()) {
            return new KingDirection();
        }

        String message = "Unknown figure for create direction";
        throw new IllegalArgumentException(message);
    }


}
