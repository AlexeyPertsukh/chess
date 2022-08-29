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
    private final FigureColor myColor;
    private final List<CheckList> checkLists = new ArrayList<>();
    private final boolean[][] array = new boolean[Board.SIZE][Board.SIZE];

    public Danger(Board board, FigureColor myColor) {
        this.board = board;
        this.myColor = myColor;

        update();
    }

    public FigureColor getMyColor() {
        return myColor;
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

    protected void update() {
        checkLists.clear();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Piece piece = board.get(cell);
                if (isEnemy(piece, myColor)) {
                    updateArray(cell);
                }
            }
        }
    }

    private static boolean isEnemy(Piece piece, FigureColor myColor) {
        return !piece.isNull() && piece.getColor() != myColor;
    }

    private void updateArray(Cell cellEnemy) {
        Piece enemy = board.get(cellEnemy);
        Distance distance = enemy.getDistance();
        Offset[] offsets = enemy.getOffsetsAttack();

        for (Offset o : offsets) {

            Cell check = cellEnemy;
            CheckList list = new CheckList();
            list.add(cellEnemy);
            while (true) {

                check = check.sum(o);
                if (!board.isCorrect(check)) {
                    break;
                }

                array[check.row][check.column] = ON;
                list.add(check);

                Piece other = board.get(check);
                if (isMyKing(other, myColor)) {
                    list.remove(check);
                    checkLists.add(list);
                }

                if (!other.isNull() || distance == Distance.ONE) {
                    break;
                }

            }
        }
    }

    private static boolean isMyKing(Piece piece, FigureColor myColor) {
        return piece.isKing() && piece.getColor() == myColor;
    }

}
