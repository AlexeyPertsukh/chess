package com.company.model.piece;

import com.company.model.chess_exception.ChessException;
import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.direction.Distance;
import com.company.model.piece.figure.direction.Offset;

public class Piece implements INull {
    private final Figure figure;
    protected int moveCount = 0;

    public Piece(Figure figure) {
        this(figure, 0);
    }

    public Piece(Figure figure, int moveCount) {
        this.figure = figure;
        this.moveCount = moveCount;
    }

    public char getIcon() {
        return figure.getIcon();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public Offset[] getOffsetsMove() {
        return figure.getDirection().getOffsetsMove();
    }

    public Offset[] getOffsetsAttack() {
        return figure.getDirection().getOffsetsAttack();
    }

    public Distance getDistance() {
        return figure.getDirection().getDistance();
    }

    public Rank getRank() {
        return figure.getRank();
    }

    public char getLetter() {
        return figure.getLetter();
    }

    public boolean isPawn() {
        return figure.getRank() == Rank.PAWN;
    }

    public boolean isRock() {
        return figure.getRank() == Rank.ROCK;
    }

    public boolean isKnight() {
        return figure.getRank() == Rank.KNIGHT;
    }

    public boolean isBishop() {
        return figure.getRank() == Rank.BISHOP;
    }

    public boolean isQueen() {
        return figure.getRank() == Rank.QUEEN;
    }

    public boolean isKing() {
        return figure.getRank() == Rank.KING;
    }

    public Team getTeam() {
        return figure.getTeam();
    }

    public void incMoveCount() {
        moveCount++;
    }

    public void decMoveCount() {
        moveCount--;
        if (moveCount < 0) {
            moveCount = 0;
        }
    }

    public boolean isMoved() {
        return moveCount > 0;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        String message = String.format("piece %s has no cloning", getClass().getName());
        throw new ChessException(message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Piece other = (Piece) o;
        return moveCount == other.moveCount && figure == other.figure;
    }
}
