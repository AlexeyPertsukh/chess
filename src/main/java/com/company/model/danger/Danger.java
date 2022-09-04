package com.company.model.danger;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.direction.Distance;
import com.company.model.piece.figure.direction.Offset;
import com.company.model.piece.Piece;

//Таблица клеток, находящихся под боем
public class Danger {
    private final static boolean ON = true;

    private final Board board;
    private final Team team;
    private final boolean[][] array = new boolean[Board.SIZE][Board.SIZE];

    public Danger(Board board, Team team) {
        this.board = board;
        this.team = team;

        update();
    }

    public Team getTeam() {
        return team;
    }

    public boolean[][] toArray() {
        return array;
    }

    public boolean isUnderAttack(Cell cell) {
        return array[cell.row][cell.column];
    }

    protected void update() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Piece piece = board.get(cell);
                if (isEnemy(piece, team)) {
                    updateArray(cell);
                }
            }
        }
    }

    private static boolean isEnemy(Piece piece, Team myTeam) {
        return !piece.isNull() && piece.getTeam() != myTeam;
    }

    private void updateArray(Cell cellEnemy) {
        Piece enemy = board.get(cellEnemy);
        Distance distance = enemy.getDistance();
        Offset[] offsets = enemy.getOffsetsAttack();

        for (Offset o : offsets) {

            Cell check = cellEnemy;
            while (true) {

                check = check.sum(o);
                if (!board.isCorrect(check)) {
                    break;
                }

                array[check.row][check.column] = ON;

                Piece other = board.get(check);

                if (!other.isNull() || distance == Distance.ONE) {
                    break;
                }

            }
        }
    }

    private static boolean isMyKing(Piece piece, Team myTeam) {
        return piece.isKing() && piece.getTeam() == myTeam;
    }

    private boolean isEnemy(Piece piece) {
        return !piece.isNull() && piece.getTeam() != team;
    }
}
