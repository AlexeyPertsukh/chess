package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Obstruction;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

public class Step extends MoveType {

    public Step(Board board) {
        super(board);
    }

    @Override
    public void verify(Cell from, Cell to, DangerMatrix dangerMatrix) {
        if(board == null) System.out.println("NUL!!!!!!!");
        Unit unitFrom = board.get(from);
        Unit unitTo = board.get(to);

        if (!isCorrectDirection(board, from, to)) {
            String message = "Ход невозможен: фигура так не ходит";
            throw new IllegalArgumentException(message);
        }

        if (!isWayWithoutObstacles(from, to)) {
            String message = "Ход невозможен: на пути фигуры есть препятствие";
            throw new IllegalArgumentException(message);
        }

        if (!unitTo.isNull() && unitFrom.getColor() == unitTo.getColor()) {
            String message = String.format("Ход невозможен: в клетке %s находится фигура того же цвета", Board.toPosition(to));
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void execute(Cell from, Cell to) {
        Unit unit = board.remove(from);
        board.insert(unit, to);
        unit.incMoveCount();
    }


    private boolean isCorrectDirection(Board board, Cell from, Cell to) {
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

    protected boolean isWayWithoutObstacles(Cell from, Cell to) {
        int offsetRow = sign(to.row - from.row);
        int offsetColumn = sign(to.column - from.column);

        Unit unit = board.get(from);
        if (!unit.isNull() && unit.getObstruction() == Obstruction.IGNORE) {
            return true;
        }

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

}
