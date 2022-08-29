package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.direction.Distance;
import com.company.model.piece.figure.direction.Offset;

public class Piece implements INull {
    private final Figure figure;
    private int moveCount = 0;

    public Piece(Figure figure) {
        this.figure = figure;
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

    public  boolean isRock() {
        return figure.getRank() == Rank.ROCK;
    }

    public  boolean isKnight() {
        return figure.getRank() == Rank.KNIGHT;
    }

    public  boolean isBishop() {
        return figure.getRank() == Rank.BISHOP;
    }

    public  boolean isQueen() {
        return figure.getRank() == Rank.QUEEN;
    }

    public  boolean isKing() {
        return figure.getRank() == Rank.KING;
    }

    public Team getTeam() {
        return figure.getTeam();
    }

    public void incMoveCount() {
        moveCount++;
    }

    public boolean isMoved() {
        return moveCount > 0;
    }

    @Override
    public boolean isNull() {
        return false;
    }

}
