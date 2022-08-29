package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.FigureColor;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

//Таблица клеток, находящихся под боем
public class Danger {
    private final static boolean ON = true;

    private final Board board;
    private final List<CheckList> checkLists = new ArrayList<>();
    private boolean[][] array = new boolean[Board.SIZE][Board.SIZE];

    public Danger(Board board, FigureColor aggressorColor) {
        this.board = board;

        update(aggressorColor);
    }

    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public boolean[][] toArray() {
        return array;
    }

    public boolean isUnderAttack(Cell cell) {
        return array[cell.row][cell.column];
    }

    public boolean isCheck() {
        return !checkLists.isEmpty();
    }

    protected void update(FigureColor aggressorColor) {
        checkLists.clear();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Piece piece = board.get(cell);
                if (!piece.isNull() && piece.getColor() == aggressorColor) {
                    updateArray(cell);
                }
            }
        }
    }

    private void updateArray(Cell cell) {
        Piece piece = board.get(cell);
        Distance distance = piece.getDistance();
        Offset[] offsets = piece.getOffsetsAttack();

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

                Piece other = board.get(check);
                if (other.isKing() && other.getColor() != piece.getColor()) {
                    list.remove(check);
                    checkLists.add(list);
                }

                if (!other.isNull() || distance == Distance.ONE) {
                    break;
                }

            }
        }
    }

}
