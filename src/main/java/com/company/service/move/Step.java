package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.player.Player;
import com.company.model.unit.Unit;
import com.company.model.board.Way;

public class Step extends Move {

    private static final String MARKER = "Move failed";

    public Step(Board board) {
        super(board);
    }

    @Override
    protected void action(Way way) {
        Unit unit = board.transfer(way);
        unit.incMoveCount();
    }

    @Override
    protected Cell[] commandToCells(Player player, Command command) {
        String[] strings = command.getString().split("-");
        return new Cell[]{Board.toCell(strings[0]), Board.toCell(strings[1])};
    }

    @Override
    protected void specialVerify(Way way, DangerMatrix dangerMatrix) {
        Unit unitFrom = board.get(way.from);
        Unit unitTo = board.get(way.to);

        if (!isCorrectDirection(board, way)) {
            String message = String.format("%s: illegal move %s", MARKER, wayToString(way));
            throw new IllegalArgumentException(message);
        }

        if (!isWayWithoutObstacles(way)) {
            String message = String.format("%s: obstacles in the way of pieces", MARKER);
            throw new IllegalArgumentException(message);
        }

        if (!unitTo.isNull() && unitFrom.getColor() == unitTo.getColor()) {
            String message = String.format("%s: в клетке %s находится фигура того же цвета", MARKER, Board.toPosition(way.to));
            throw new IllegalArgumentException(message);
        }

        if (unitFrom.isKing() && dangerMatrix.isUnderAttack(way.to)) {
            String message = String.format("%s: клетка %s находится под боем", MARKER, Board.toPosition(way.to));
            throw new IllegalArgumentException(message);
        }
    }


    @Override
    protected String messageNoUnit(Cell cell) {
        return String.format("%s: на клетке %s нет фигуры", MARKER, Board.toPosition(cell));
    }

    @Override
    protected String messageAlienUnit(Cell cell) {
        return String.format(" %s: фигура на %s принадлежит другому игроку", MARKER, Board.toPosition(cell));
    }

    private boolean isCorrectDirection(Board board, Way way) {
        Cell from = way.from;
        Cell to = way.to;

        Unit unit = board.get(from);
        boolean attack = !board.get(to).isNull();
        Offset[] offsets = attack ? unit.getOffsetsAttack() : unit.getOffsetsMove();
        Distance distance = unit.getDistance();

        int row = to.row - from.row;
        int column = to.column - from.column;

        if (!attack && unit.isPawn() && from.column == to.column &&
                ((from.row == 1 && to.row == 3) || (from.row == Board.SIZE - 2 && to.row == Board.SIZE - 4))) {
            return true;
        }

        if (distance == Distance.UNLIM) {
            if (row == 0) {
                column = sign(column);
            } else if (column == 0) {
                row = sign(row);
            } else if (Math.abs(column) == Math.abs(row)) {
                column = sign(column);
                row = sign(row);
            }
        }

        Offset check = new Offset(column, row);

        for (Offset o : offsets) {
            if (check.equals(o)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isWayWithoutObstacles(Way way) {
        Cell from = way.from;
        Cell to = way.to;

        Unit unit = board.get(from);

        if (unit.getDistance() == Distance.ONE) {
            Unit other = board.get(to);
            return other.isNull() || other.getColor() != unit.getColor();
        }

        int offsetRow = sign(to.row - from.row);
        int offsetColumn = sign(to.column - from.column);

        Cell cell = from;
        while (true) {
            cell = cell.sum(offsetColumn, offsetRow);
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


    private static String wayToString(Way way) {
        return Board.toPosition(way.from) + "-" + Board.toPosition(way.to);
    }


}
