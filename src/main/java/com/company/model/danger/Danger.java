package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.FigureColor;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

//Таблица клеток, находящихся под боем
public class Danger {
    private final static boolean ON = true;

    private final Board board;

    public Danger(Board board) {
        this.board = board;
    }

    public DangerMatrix toMatrix(FigureColor aggressorColor) {
        boolean[][] array = toArray(aggressorColor);
        return new DangerMatrix(array);
    }

    private boolean[][] toArray(FigureColor aggressorColor) {
        boolean[][] out = new boolean[Board.SIZE][Board.SIZE];

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Unit unit = board.get(cell);
                if (!unit.isNull() && unit.getColor() == aggressorColor) {
                    updateArray(out, cell, aggressorColor);
                }
            }

        }

        return out;
    }

    private void updateArray(boolean[][] array, Cell cell, FigureColor aggressorColor) {
        Unit unit = board.get(cell);
        Distance distance = unit.getDistance();
        Offset[] offsets = unit.getOffsetsAttack();

        for (Offset o : offsets) {
            Cell check = new Cell(cell.column, cell.row);

            int oco = o.column;
            int oro = o.row;

            while (true) {

                int co = check.column;
                int ro = check.row;

                check = new Cell(check.column + o.column, check.row + o.row);
                if (!board.isCorrect(check)) {
                    break;
                }
                Unit other = board.get(check);

                if (!other.isNull()) {
                    array[check.row][check.column] = ON;
                    break;
                }

                array[check.row][check.column] = ON;

                if (distance == Distance.ONE) {
                    break;
                }

            }
        }
    }

}
