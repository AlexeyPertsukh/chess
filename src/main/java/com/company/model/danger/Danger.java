package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.FigureColor;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

import java.util.ArrayList;
import java.util.List;

//Таблица клеток, находящихся под боем
public class Danger {
    private final static boolean ON = true;

    private final Board board;
    private final List<CheckList> checkLists = new ArrayList<>();
    private final boolean[][] array;

    public Danger(Board board, FigureColor aggressorColor) {
        this.board = board;

        array = createArray(aggressorColor);
    }

    public List<Danger.CheckList> getCheckLists() {
        return checkLists;
    }

    public boolean[][] toArray() {
        return array.clone();
    }

    public boolean isUnderAttack(Cell cell) {
        return array[cell.row][cell.column];
    }

    public boolean isCheck() {
        return !checkLists.isEmpty();
    }

    public boolean[][] createArray(FigureColor aggressorColor) {
        boolean[][] out = new boolean[Board.SIZE][Board.SIZE];
        checkLists.clear();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Unit unit = board.get(cell);
                if (!unit.isNull() && unit.getColor() == aggressorColor) {
                    updateArray(out, cell);
                }
            }
        }
        return out;
    }

    private void updateArray(boolean[][] array, Cell cell) {
        Unit unit = board.get(cell);
        Distance distance = unit.getDistance();
        Offset[] offsets = unit.getOffsetsAttack();

        for (Offset o : offsets) {

            Cell check = cell;
            CheckList list = new CheckList();
            list.add(cell);
            while (true) {

                check = check.sum(o);
                if (!board.isCorrect(check)) {
                    break;
                }

                array[check.row][check.column] = ON;
                list.add(check);

                Unit other = board.get(check);
                if (other.isKing() && other.getColor() != unit.getColor()) {
                    list.remove(check);
                    checkLists.add(list);
                }

                if (!other.isNull() || distance == Distance.ONE) {
                    break;
                }

            }
        }
    }

    public static class CheckList extends ArrayList<Cell> {
    }

}
